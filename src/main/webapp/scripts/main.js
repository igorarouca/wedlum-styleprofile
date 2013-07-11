require.config({
  shim: {
    underscore: {
      exports: '_'
    },

    backbone: {
      deps: ['underscore', 'jquery'],
      exports: 'Backbone'
    }
  },

  paths: {
    jquery: 'lib/jquery-1.7.1',
    underscore: 'lib/underscore-1.4.4',
    backbone: 'lib/backbone-1.0.0',
    templates: '../templates'
  }
});

require(
    [ 'app' ], function(App){
      App.initialize();
    });

