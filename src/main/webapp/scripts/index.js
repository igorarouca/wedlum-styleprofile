var thumbUpAction = "api/colors/thumbUp/";
var thumbDownAction = "api/colors/thumbDown/";

var BubbleView = Backbone.View.extend({

	events: {
		"click img" : "returnToPhotoList"
	},

	returnToPhotoList : function() {
		var thumbChoice = this.options.target === "Up" ? '#thumb-up' : '#thumb-down';
		var thumbAction = this.options.target === "Up" ? thumbUpAction : thumbDownAction;
		var $img = this.$("img");
		var imgSrc = $img.attr("src");
		var colorSwatchId = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);

		$(".color-swatch-bg div img[src='" + imgSrc + "']").parent().show();
		this.$("a").remove();
		this.$el.append(this.options.emptyBubble.clone());
		$("#photo-list " + thumbChoice).show();
		$.ajax({ url: thumbAction + "remove/" + colorSwatchId });
	},

	isEmpty: function() {
		return this.$("img").length === 0;
	},

	receivePhoto: function(photo) {
		var solidBubbleClone = this.options.solidBubble.clone();
		$(solidBubbleClone.find("img")).replaceWith(photo);
		this.$el.append(solidBubbleClone);
//		this.$("span").remove();
	}

});

var PhotoView = Backbone.View.extend({
	events: {
		"click .thumb-up" : "thumbUp",
		"click .thumb-down" : "thumbDown"
	},

	colorSwatchId : function() {
		var imgSrc = this.$("#color-swatch").attr("src");
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
		alert("Maximum like/unlike limit reached" );			
	},

	appendPhotoToFirstAvailableBubble: function(target) {
		var $photo = this.$("#color-swatch");
		var thumbChoice = target == "Up" ? '#thumb-up' : '#thumb-down';
		var sortingArea = target == "Up" ? this.options.ups : this.options.downs;
		var that = this;

		for(var index = 0; index < sortingArea.length; index++) {
			var bubble = sortingArea[index];		
			if(bubble.isEmpty()) {
				bubble.receivePhoto($photo.clone());			
				this.animatePhotoSelection(target);
				return;
			}
		}

		that.preventFurtherColorSwatchSelections(thumbChoice);
	},

	animatePhotoSelection: function(target) {
		this.$el.removeClass("color-swatch-bg");
		this.$el.addClass("color-swatch-bg-selected");
		this.animateThumb(target);
	},

	animateThumb: function(target) {
		var thumbImg = target === "Up" ? '#image-thumb-up' : '#image-thumb-down';
		var $thumbImg = this.$(thumbImg);
		$thumbImg.show();
		setTimeout(function(){ $thumbImg.fadeOut(); }, 1000);
	},

	movePhotoTo: function(target) {
		this.appendPhotoToFirstAvailableBubble(target);
	}
});

var IndexView = Backbone.View.extend({

	createBubbleViews: function(target) {
		var sortingArea = target === "Up" ? '#feedback-thumb-ups' : '#feedback-thumb-downs';
		var $bubbleProto = $(sortingArea + " .feedback-bubble");

		var solidBubble = $($bubbleProto.get(0)).find("a");
		var emptyBubble = $($bubbleProto.get(1)).find("span");

		solidBubble.parent().append(emptyBubble.clone());
		solidBubble.remove();

		var bubbleViews = [];
		$bubbleProto.each(function() {
			bubbleViews.push(new BubbleView( { el : $(this), target: target, solidBubble: solidBubble, emptyBubble: emptyBubble }));
		});

		return bubbleViews;
	},

	createPhotoViewsUsingFirstPhotoAsPrototype: function(ups, downs) {
		var $divProto = $("#photo-div-1");

		$(".color-swatch-bg").each(function() {
			var $this = $(this);
			var colorSwatchSrc = $this.find("img").first().attr("src");
			$this.html($divProto.html());
			$(this).find("#color-swatch").attr("src", colorSwatchSrc);

			new PhotoView({ el: $(this), ups: ups, downs: downs });
		});

	},

	render: function() {
		var ups = this.createBubbleViews("Up");
		var downs = this.createBubbleViews("Down");

		this.createPhotoViewsUsingFirstPhotoAsPrototype(ups, downs);
	}

});

$(function() {

	new IndexView().render();

});

var wedlum = wedlum || {};
wedlum.ColorSessionSettings = {
		selectionLimit: 4
};

