define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/home/show.html'
], function($, _, Backbone, show){

  var HomeView = Backbone.View.extend({
    el: $("#container"),

    render: function(){

      this.$el.html(show);
    }
  });

  return HomeView;
});
