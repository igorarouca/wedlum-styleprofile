var thumbUpAction = "api/colors/thumbUp/";
var thumbDownAction = "api/colors/thumbDown/";

var BubbleView = Backbone.View.extend({
	
	events: {
		"click img" : "returnToColorSwatchList",
	},
	
	returnToColorSwatchList : function() {
		var thumbChoice = this.options.target === "Up" ? '#ThumbUp' : '#ThumbDown';
		var thumbAction = this.options.target === "Up" ? thumbUpAction : thumbDownAction;
		var $img = this.$("img");
		var imgSrc = $img.attr("src");
		var colorSwatchId = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
		
		$(".Photo img[src='" + imgSrc + "']").parent().show();
		$img.remove();
		$("#PhotoGroup " + thumbChoice).show();
		$.ajax({ url: thumbAction + "remove/" + colorSwatchId });
	},
	
});

var PhotoView = Backbone.View.extend({
	events: {
		"click #ThumbUp" : "thumbUp",
		"click #ThumbDown" : "thumbDown"
	},

	colorSwatchId : function() {
		var imgSrc = this.$("#ColorSwatch").attr("src");
		return imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
	},

	thumbUp: function(event) {
		event.preventDefault();
		$.ajax({ url: thumbUpAction + "add/" + this.colorSwatchId() });
		this.movePhotoTo("Up");
	},

	thumbDown: function(event) {
		event.preventDefault();
		$.ajax({ url: thumbDownAction + "add/" + this.colorSwatchId() });
		this.movePhotoTo("Down");
	},

	preventFurtherColorSwatchSelections: function(thumbChoice) {
		$("#PhotoGroup " + thumbChoice).hide();			
	},

	appendPhotoToFirstAvailableBubble: function(target) {
		var $photo = this.$("#ColorSwatch");
		var thumbChoice = target === "Up" ? '#ThumbUp' : '#ThumbDown';
		var sortingArea = target === "Up" ? '#PhotoThumbups' : '#PhotoThumbdowns';
		var that = this;

		$(sortingArea).find(".PreferenceBubble").each(function(index) {
			if($(this).find("img").length == 0) {
				$(this).append($photo.clone());
				$photo.parent().hide();
				if(index === 3) {
					that.preventFurtherColorSwatchSelections(thumbChoice);
				}
				return false;
			}
		});
	},

	animateThumb: function(target) {
		var thumbImg = target === "Up" ? '#ThumbUpImg' : '#ThumbDownImg';
		var $thumbImg = this.$(thumbImg);
		$thumbImg.show();
		setTimeout(function(){ $thumbImg.fadeOut(); }, 1000);
	},

	movePhotoTo: function(target) {
		this.appendPhotoToFirstAvailableBubble(target);
		this.animateThumb(target);
	}
});

var IndexView = Backbone.View.extend({

	createBubbleViews: function(target) {
		var sortingArea = target === "Up" ? '#PhotoThumbups' : '#PhotoThumbdowns';
		var $bubbleProto = $(sortingArea + " .PreferenceBubble");

		$bubbleProto.empty();
		$bubbleProto.each(function() {
			new BubbleView( { el : $(this), target: target });
		});		
	},

	createPhotoViewsUsingFirstPhotoAsPrototype: function() {
		var $divProto = $("#1stPhoto");

		$(".photo-list li").each(function() {
			var $this = $(this);
			var colorSwatchSrc = $this.find("img").first().attr("src");
			$this.html($divProto.html());
			$(this).find("#ColorSwatch").attr("src", colorSwatchSrc);

			new PhotoView({ el: $(this) });
		});

	},

	render: function() {
		this.createBubbleViews("Down");
		this.createBubbleViews("Up");

		this.createPhotoViewsUsingFirstPhotoAsPrototype();
	}

});

$(function() {

	new IndexView().render();

});

