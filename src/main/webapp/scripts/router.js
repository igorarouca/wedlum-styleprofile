define([
  'jquery',
  'underscore',
  'backbone',
  'views/home/show'
], function($, _, Backbone, HomeView) {

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
        var view = new HomeView();
        view.render();
    });

    // Unlike the above, we don't call render on this view as it will handle
    // the render call internally after it loads data. Further more we load it
    // outside of an on-route function to have it loaded no matter which page is
    // loaded initially.

    //var footer = new FooterView();

    Backbone.history.start();
  };
  return {
    initialize: initialize
  };
});
