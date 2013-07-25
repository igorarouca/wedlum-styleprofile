var wedlum  = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.survey = wedlum.styleprofile.survey || {};

wedlum..styleprofile.survey.Photo = Backbone.Model.extend();

wedlum.styleprofile.survey.Session = Backbone.Model.extend({
    limit: 4,
    allPhotos: new Backbone.Collection(),
    likes: new Backbone.Collection(),
    dislikes: new Backbone.Collection(),

    initialize: function(){
        this.set('likesCount', 0);
        this.set('dislikesCount', 0);
        this.set('limit', this.limit);
    },

    addPhoto: function(photo){
        this.allPhotos.add(new wedlum.session.Photo({photo: photo, id: photo, status: 'default'}));
    },

    like: function (photo){
        this.reset(photo);
        if (this.likes.length == this.limit){
            wedlum.notifier.warning("You have already liked " + this.limit + " photos");
            return;
        }
        this.likes.add(photo);
        this.set('likesCount', this.likes.length);
        photo.set('status', 'like');
    },

    dislike: function (photo){
        this.reset(photo);
        if (this.dislikes.length == this.limit){
            wedlum.notifier.warning("You have already disliked " + this.limit + " photos");
            return;
        }
        this.dislikes.add(photo);
        this.set('dislikesCount', this.dislikes.length);
        photo.set('status', 'dislike');
    },

    reset: function (photo){
        this.likes.remove(photo);
        this.dislikes.remove(photo);
        photo.set('status', 'default');
        this.set('likesCount', this.likes.length);
        this.set('dislikesCount', this.dislikes.length);
    },

    statusChange: function(photo){
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
		"click .thumb-down" : "thumbDown",
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

	thumbDown: function(event) {
		event.preventDefault();
        this.session.dislike(this.model);
	},

    reset: function(event) {
        this.session.reset(this.model);
    },

    render: function(){
        var input = this.model.attributes;
        parameters = _(input).extend(this.session.attributes);
        this.$el.html(this.template(parameters));
        return this;
    }
});

$(function() {
    wedlum.styleprofile.survey.session = new wedlum.styleprofile.survey.Session();
    wedlum.styleprofile.survey.session.addPhoto("PurpleL.jpg");
    wedlum.styleprofile.survey.session.addPhoto("OrangeL.jpg");
    wedlum.styleprofile.survey.session.addPhoto("PurpleD.jpg");
    wedlum.styleprofile.survey.session.addPhoto("RedD.jpg");
    wedlum.styleprofile.survey.session.addPhoto("RedL.jpg");
    wedlum.styleprofile.survey.session.addPhoto("YellowD.jpg");

    var view = new PhotoListView({model: wedlum.session.session});
    view.el = $("ul#photo-group-list")[0];
    view.render();

})
