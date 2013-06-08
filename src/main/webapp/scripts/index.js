$(function() {

	var $bubbleProto = $(".PreferenceBubble");
	$bubbleProto.empty();

	var PhotoView = Backbone.View.extend({
		events: {
			"click #ThumbUp" : "thumbUp",
			"click #ThumbDown" : "thumbDown"
		},

		thumbUp: function(event) {
			event.preventDefault();
			var turnOff = this.movePhotoTo("#PhotoThumbups");
			if (turnOff) {
				$("#PhotoGroup #ThumbUp").remove();
			}
		},

		thumbDown: function(event) {
			event.preventDefault();
			var turnOff = this.movePhotoTo("#PhotoThumbdowns");
			if (turnOff) {
				$("#PhotoGroup #ThumbDown").remove();
			}
		},

		movePhotoTo: function(sortingArea) {
			var reachedLimit = false;
			var $img = this.$("img");
			$(sortingArea).children().each(function(index) {
				if(!$(this).find("img").length) {
					$(this).append($img);
					if(index === 3) {
						reachedLimit = true;
					}
					return false;
				}
			});
			this.$("span").remove();
			return reachedLimit;
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
