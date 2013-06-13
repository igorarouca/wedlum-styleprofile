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
			this.movePhotoTo("Up");
		},

		thumbDown: function(event) {
			event.preventDefault();
			this.movePhotoTo("Down");
		},

		movePhotoTo: function(target) {
			var $photo = this.$("#ColorSwatch");
			var thumbChoice = target === "Up" ? '#ThumbUp' : '#ThumbDown';
			var thumbImg = target === "Up" ? '#ThumbUpImg' : '#ThumbDownImg';
			var sortingArea = target === "Up" ? '#PhotoThumbups' : '#PhotoThumbdowns';

			$(sortingArea).find(".PreferenceBubble").each(function(index) {
				if(!$(this).find("img").length) {
					$(this).append($photo);
					if(index === 3) {
						// Remove link option from other photos
						$("#PhotoGroup " + thumbChoice).remove();
					}
					return false;
				}
			});

			// Remove both links only from myself
			this.$("span").remove();
			var $thumbImg = this.$(thumbImg);
			$thumbImg.show();
			setTimeout(function(){ $thumbImg.fadeOut(); }, 1000);
		}
	});

	var $divProto = $("#1stPhoto");

	$(".photo-list li").each(function() {
		var $this = $(this);
		var colorSwatchSrc = $this.find("img").first().attr("src");
		$this.html($divProto.html());
		$(this).find("#ColorSwatch").attr("src", colorSwatchSrc);

		new PhotoView({ el: $(this) });
	});

});

