var thumbUpAction = "api/colors/thumbUp/";
var thumbDownAction = "api/colors/thumbDown/";

var wedlum  = wedlum || {};
wedlum.session  = wedlum.session || {};

wedlum.session.Photo = Backbone.Model.extend();

wedlum.session.Session = Backbone.Model.extend({
    limit: 4,
    allPhotos: new Backbone.Collection(),
    likes: new Backbone.Collection(),
    dislikes: new Backbone.Collection(),

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
        photo.set('status', 'like');
    },

    dislike: function (photo){
        this.reset(photo);
        if (this.dislikes.length == this.limit){
            wedlum.notifier.warning("You have already disliked " + this.limit + " photos");
            return;
        }
        this.dislikes.add(photo);
        photo.set('status', 'dislike');
    },

    reset: function (photo){
        this.likes.remove(photo);
        this.dislikes.remove(photo);
        photo.set('status', 'default');
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
            var view = new PhotoView({model: photo});
            view.session = that.model;
           $(that.el).append(view.render().el);
        });
    }
});

var PhotoView = Backbone.View.extend({

    initialize: function(){
        var that = this;
        this.model.on("change", this.render, this);
    },

	events: {
		"click .thumb-up" : "thumbUp",
		"click .thumb-down" : "thumbDown"
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

    render: function(){
        var input = this.model.attributes;
        this.$el.html(this.template(input));
        return this;
    }
});

$(function() {
    wedlum.session.session= new wedlum.session.Session();
    wedlum.session.session.addPhoto("PurpleL.jpg");
    wedlum.session.session.addPhoto("OrangeL.jpg");
    wedlum.session.session.addPhoto("PurpleD.jpg");

    var view = new PhotoListView({model: wedlum.session.session});
    view.el = $("ul#photo-group-list")[0];
    view.render();

})
