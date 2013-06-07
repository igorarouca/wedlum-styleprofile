$(function() {

	var $miniProto = $(".ColorSwatchMini");
	$miniProto.remove();

	var ColorSwatchView = Backbone.View.extend({
		events: {
			"click img" : "colorSwatchLiked",
			"click #Like" : "colorSwatchLiked",
			"click #Dislike" : "colorSwatchDisliked"
		},

		colorSwatchLiked: function(event) {
			event.preventDefault();
			this.moveColorSwatchTo("#ImageLikes");
		},

		colorSwatchDisliked: function(event) {
			event.preventDefault();
			this.moveColorSwatchTo("#ImageDislikes");
		},

		moveColorSwatchTo: function(sortingArea) {
			var $img = this.$("img");
			$img.addClass("ColorSwatchMini");
			$(sortingArea).append($img);
			this.$("p").remove();
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
