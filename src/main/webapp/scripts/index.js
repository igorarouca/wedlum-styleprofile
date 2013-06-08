$(function() {

	var $bubbleProto = $(".PreferenceBubble");
	$bubbleProto.children().remove();

	var PhotoView = Backbone.View.extend({
		events: {
			"click #ThumbUp" : "thumbUp",
			"click #ThumbDown" : "thumbDown"
		},

		thumbUp: function(event) {
			event.preventDefault();
			this.movePhotoTo("#PhotoThumbups");
		},

		thumbDown: function(event) {
			event.preventDefault();
			this.movePhotoTo("#PhotoThumbdowns");
		},

		movePhotoTo: function(sortingArea) {
			var $img = this.$("img");
			$img.addClass("PreferenceBubble");
			$(sortingArea).append($img);
			this.$("span").remove();
		}
	});

	var divProto = $("#1stPhoto");

	$(".Photo").each(function() {
		var imgSrc = $(this).find("img").attr("src");
		var divClone = divProto.clone();
		$(this).children().remove();
		divClone.find("img").attr("src", imgSrc);
		$(this).append(divClone);
		new PhotoView({ el: $(this) });
	});

});
