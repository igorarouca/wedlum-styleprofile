define([
    'underscore',
    'backbone'
], function( _, Backbone) {

    var PhotoModel = Backbone.Model.extend({
      defaults : {
        filename: "something",
        status: "default"
      },

      validate: function(attributes) {
        //example of a validation
        // if(!attributes.filename) {
        //   return "Every file must have a filename."
        // }
      }
    });

    return PhotoModel;
});
