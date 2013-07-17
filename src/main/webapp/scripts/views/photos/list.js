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

      // var collection = new PhotosCollection();
      // var data = {
      //   photos: collection,
      //   _: _
      // };

      // //perhaps need to render the photos individually before passing to collection template
      // var compiled = _.template(template, data);
      // this.$el.html(compiled);

      // data.photos.each(function (photo) {
      //   var view = new PhotoView(photo);
      //   $(this.$el).append(view.render().el);
      // });
      this.collection.each( function(photo) {
        var pv = new PhotoView({ model: photo });
        this.$el.append(pv.el);
      }, this);

      return this;
    }
  });

  return PhotosListView;
});
