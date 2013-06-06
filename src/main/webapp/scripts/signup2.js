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

	var UserCollection = Backbone.Collection.extend({
		model : UserModel,
		url: "users.json"
	});

	var UserListView = Backbone.View.extend({
		el : '#user-list',

		initialize : function() {
			this.collection.on('reset', this.render, this);
			this.one_el = this.$("#user-el");
			this.one_el.remove();
		},

		renderOne : function(user, one_el) {
			var view = new UserView({
				model : user,
				el : one_el.clone()
			});
			var container = this.$el;
			container.append(view.render().el);
		},

		render : function() {
			var that = this;
			var one_el = this.one_el;
			this.collection.each(function(user) {
				that.renderOne(user, one_el);
			});
			return this;
		},

	});

	var users = new UserCollection();
	var userListView = new UserListView({ collection : users });

	users.fetch({
		mimeType : 'application/json',
		cache : false,
		success : function() { userListView.render(); },
		error : function() {
			console.log(arguments);
		}
	});
//	
//	var userModel = new UserModel();
//	var userView = new UserView({ model: userModel });
//	
//	userModel.fetch({
//		mimeType: 'application/json',
//		cache: false,
//		success: function(data) { console.log(data); },
//		error: function() { console.log(arguments); }
//	});

});
