var wedlum  = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.survey = wedlum.styleprofile.survey || {};

wedlum.styleprofile.survey.Photo = Backbone.Model.extend();

wedlum.styleprofile.survey.Session = Backbone.Model.extend({
    limit: 4,
    allPhotos: new Backbone.Collection(),
    likes: new Backbone.Collection(),

    initialize: function() {
        this.set('likesCount', 0);
        this.set('limit', this.limit);
    },

    setPhotos: function(photos) {
        photos = _(photos).map(function(photo) { return new wedlum.styleprofile.survey.Photo({ photo: photo, id: photo, status: 'default' })});
        this.allPhotos.add(photos);
    },

    like: function (photo) {
        this.reset(photo);
        if (this.likes.length == this.limit){
            wedlum.notifier.warning("You have already liked " + this.limit + " photos");
            return;
        }
        this.likes.add(photo);
        this.set('likesCount', this.likes.length);
        photo.set('status', 'like');
        if (this.likes.length == this.limit){
            this.trigger("complete");
        }
    },

    reset: function (photo) {
        this.likes.remove(photo);
        photo.set('status', 'default');
        this.set('likesCount', this.likes.length);
    },

    resetAll: function () {
        this.likes.reset();
        this.allPhotos.reset();
    },

    statusChange: function(photo) {
        this.trigger("statusChange", photo);
    }
});

var PhotoListView = Backbone.View.extend({

    render: function(){
        $(this.el).html("");
        var that = this;
        this.model.allPhotos.each(function (photo){
            var view = new PhotoView({model: photo, session: that.model});
           $(that.el).append(view.render().el);
        });
    }
});

var PhotoView = Backbone.View.extend({

    initialize: function(args){
        this.session = args.session;
        this.model.on("change", this.render, this);
        this.session.on("change", this.render, this);
    },

	events: {
		"click .thumb-up" : "thumbUp",
        "click img" : "reset"
	},

    tagName:  "li",

    template: _.template($('#photo-template').html()),

	colorSwatchId : function() {
		var imgSrc = this.$("img").attr("src");
		return imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
	},

	thumbUp: function(event) {
		event.preventDefault();
        this.session.like(this.model);
	},

    reset: function(event) {
        this.session.reset(this.model);
    },

    render: function() {
        var input = this.model.attributes;
        parameters = _(input).extend(this.session.attributes);
        this.$el.html(this.template(parameters));
        return this;
    }
});

$(function() {
    var profile = {};
    var survey = new wedlum.styleprofile.survey.Survey(profile);

    wedlum.styleprofile.survey.session = new wedlum.styleprofile.survey.Session();

    var next = function() {
        survey.nextStep(function (nextStep) {
        	$("#central-photos").toggle("slide", { direction: "left" }, 800, function() {

                var imagesToPreload = [];
                _(nextStep.data).each(function(photo) {
                    imagesToPreload.push('photo-storage/' + photo);
                });
                preloadImages(imagesToPreload, function(){
                    wedlum.styleprofile.survey.session.resetAll();
                    wedlum.styleprofile.survey.session.setPhotos(nextStep.data);
                    var view = new PhotoListView({ model: wedlum.styleprofile.survey.session });
                    view.el = $("ul#photo-group-list")[0];
                    $(view.el).html("");
                    view.render();
                    $("#central-photos").attr("class", "photo-list " + nextStep.name);
                    $("#central-photos").toggle("slide", { direction: "right" }, 800);

                    wedlum.styleprofile.survey.session.on("complete", function() {
                        if (profile[nextStep.name]) return;

                        profile[nextStep.name] = {
                            allPhotos : nextStep.data,
                            likedPhotos : _(wedlum.styleprofile.survey.session.likes.models).map(function(each) { return each.id; }),
                            availableLikes: wedlum.styleprofile.survey.session.limit
                        };

                        next();
                    });

                });
        	});
        });
    };

    next();
})
