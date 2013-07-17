define([
  'jquery',
  'underscore',
  'backbone',
  'models/photos/photomodel'
], function($, _, Backbone, PhotoModel){
  var PhotosCollection = Backbone.Collection.extend({
    model: PhotoModel,

    initialize: function(){
      var photo0 = new PhotoModel({filename: "PurpleL.jpg"});
      this.add(photo0);
      var photo1 = new PhotoModel({filename: "OrangeL.jpg"});
      this.add(photo1);
      var photo2 = new PhotoModel({filename: "PurpleD.jpg"});
      this.add(photo2);
    }
  });

  return PhotosCollection;
});
