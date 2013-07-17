define([
    'underscore',
    'backbone'
], function( _, Backbone) {

    var PhotoModel = Backbone.Model.extend({
      defaults : {
        filename: "something",
        status: "default"
      }
    });

    return PhotoModel;
});
