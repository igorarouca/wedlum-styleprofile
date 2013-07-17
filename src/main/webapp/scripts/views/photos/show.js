define([
  'jquery',
  'underscore',
  'backbone',
  'text!templates/photos/show.html'
], function($, _, Backbone, template){

  var PhotoView = Backbone.View.extend({
    tagName: 'li',

    initialize: function() {
      that = this;
      this.render();
    },
//     events: {
//       "click .thumb-up" : "thumbUp",
//       "click .thumb-down" : "thumbDown"
//     },

//     tagName:  "li",

//     colorSwatchId : function() {
//       var imgSrc = this.$("img").attr("src");
//       return imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
//     },

//     thumbUp: function(event) {
//       event.preventDefault();
//           this.session.like(this.model);
//     },

//     thumbDown: function(event) {
//       event.preventDefault();
//           this.session.dislike(this.model);
//     },

    template: _.template(template),
    render: function(){
      var input = this.model.attributes;
      var compiled = _.template(template, input);
      this.$el.html(this.template(this.model.toJSON()));
      return this;
    }
  });

  return PhotoView;
});
