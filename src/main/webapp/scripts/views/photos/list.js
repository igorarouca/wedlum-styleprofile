define([
  'jquery',
  'underscore',
  'backbone',
  'models/photos/photomodel',
  'collections/photos/photoscollection',
  'views/photos/show',
], function($, _, Backbone, PhotoModel, PhotosCollection, PhotoView){

  var PhotosListView = Backbone.View.extend({
    el: $('ul#photos-list'),

    initialize: function() {
      that = this;
      this.render();
    },

    render: function(){
      this.collection.each( function(photo) {
        var pv = new PhotoView({ model: photo });
        this.$el.append(pv.el);
      }, this);

      return this;
    }
  });

  return PhotosListView;
});
