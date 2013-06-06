$(function() {

	var UserModel = Backbone.Model.extend({
		url : "user.json",

		name : function() {
			return this.get('name');
		},

		address : function() {
			return this.get('address');
		}
	});

	var UserView = Backbone.View.extend({
		el : '#user-el',

		initialize : function() {
			this.model.bind('change', this.render, this);
		},

		render : function() {
			var model = this.model;
			this.$('#name').text(model.name());
			this.$('#address').text(model.address());
			return this;
		}
	});

	 var userModel = new UserModel();
	 var userView = new UserView({ model: userModel });

	 userModel.fetch({
		 mimeType: 'application/json',
		 cache: false,
		 success: function(data) { console.log(data); },
		 error: function() { console.log(arguments); }
	 });

});
