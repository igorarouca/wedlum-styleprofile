define([
  'jquery',
  'underscore',
  'backbone',
  'views/header/show',
  'views/footer/show',
  'views/home/show',
  'views/photos/list',
  'collections/photos/photoscollection'
], function($, _, Backbone, HeaderView, FooterView, HomeView, PhotosListView, PhotosCollection) {

  var ApplicationRouter = Backbone.Router.extend({
    routes: {
      // Default
      '*actions': 'defaultAction'
    }
  });

  var initialize = function(){

    var router = new ApplicationRouter;

    router.on('route:defaultAction', function (actions) {

       // We have no matching route, lets display the home page
        // var view = new HomeView;
        // view.render();

        view = new PhotosListView({ collection: new PhotosCollection() });
        view.render().el;
    });

    // Unlike the above, we don't call render on this view as it will handle
    // the render call internally after it loads data. Further more we load it
    // outside of an on-route function to have it loaded no matter which page is
    // loaded initially.

    var header = new HeaderView();
    var footer = new FooterView();

    Backbone.history.start();
  };
  return {
    initialize: initialize
  };
});
