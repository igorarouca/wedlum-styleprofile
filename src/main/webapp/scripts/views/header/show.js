define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/header/show.html'
], function($, _, Backbone, template){

  var HeaderView = Backbone.View.extend({
    el: $("header"),

    initialize: function() {
      this.render();

      //can mimic async load with something like
      //https://github.com/thomasdavis/backbonetutorials/blob/gh-pages/examples/modular-backbone/js/views/footer/FooterView.js
    },

    render: function(){
      this.$el.html(template);
    }
  });

  return HeaderView;
});