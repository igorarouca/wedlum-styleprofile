var thumbUpAction = "api/colors/thumbUp/";
var thumbDownAction = "api/colors/thumbDown/";

var wedlum  = wedlum || {};
wedlum.session  = wedlum.session || {};

// wedlum.session.Photo = Backbone.Model.extend();

// wedlum.session.Session = Backbone.Model.extend({
//     limit: 4,
//     allPhotos: new Backbone.Collection(),
//     likes: new Backbone.Collection(),
//     dislikes: new Backbone.Collection(),

//     addPhoto: function(photo){
//         this.allPhotos.add(new wedlum.session.Photo({photo: photo, id: photo, status: 'default'}));
//     },

//     like: function (photo){
//         this.reset(photo);
//         if (this.likes.length == this.limit){
//             wedlum.notifier.warning("You have already liked " + this.limit + " photos");
//             return;
//         }
//         this.likes.add(photo);
//         photo.set('status', 'like');
//     },

//     dislike: function (photo){
//         this.reset(photo);
//         if (this.dislikes.length == this.limit){
//             wedlum.notifier.warning("You have already disliked " + this.limit + " photos");
//             return;
//         }
//         this.dislikes.add(photo);
//         photo.set('status', 'dislike');
//     },

//     reset: function (photo){
//         this.likes.remove(photo);
//         this.dislikes.remove(photo);
//         photo.set('status', 'default');
//     },

//     statusChange: function(photo){
//         this.trigger("statusChange", photo);
//     }
// });

$(function() {
    wedlum.session.session= new wedlum.session.Session();
    wedlum.session.session.addPhoto("PurpleL.jpg");
    wedlum.session.session.addPhoto("OrangeL.jpg");
    wedlum.session.session.addPhoto("PurpleD.jpg");

    var view = new PhotoListView({model: wedlum.session.session});
    view.el = $("ul#photo-group-list")[0];
    view.render();

})
