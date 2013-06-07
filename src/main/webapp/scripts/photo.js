//$(document).ready(function() {
//	
//});
$(function() {

	var photoInfoAction = "http://localhost:8042/wedlum-styleprofile/photo/retrieve?id=";

	var PhotoView = Backbone.View.extend({
		el : '#photo-el',

		events: {
			"click img" : "photoCliked",
		},

		photoCliked: function(event) {
			event.preventDefault();
			alert(this.$el.id());
		},

		render : function() {
			var model = this.model;
			this.$('photo#id').attr("href", photoInfoAction + model.id());
			this.$('photo#path').text(model.path());
			
			return this;
		}
	});

	var divProto = $("#1stColor");

	$(".ColorSwatchSmall").each(function() {
		var imgSrc = $(this).find("img").attr("src");
		$(this).children().remove();
		var divClone = divProto.clone();
		divClone.find("img").attr("src", imgSrc);
		$(this).append(divClone);
		new ColorSwatchView({ el: $(this) });
	});

});
