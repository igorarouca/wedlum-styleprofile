define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/home/show.html'
], function($, _, Backbone, homeTemplate){

  var HomeView = Backbone.View.extend({
    el: $("#container"),

    render: function(){

      this.$el.html(homeTemplate);
    }
  });

  return HomeView;
});
