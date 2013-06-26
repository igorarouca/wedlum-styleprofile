/*!
 * Bootstrap BigModal v1.0.0
 *
 * Copyright 2013 Andrew Rowls
 * Licensed under the MIT License
 *
 * https://github.com/eternicode/bootstrap-bigmodal
 */
;(function($){
    function update(modal) {
        modal.find('.modal-body').outerHeight(
            modal.innerHeight() -
                modal.find('.modal-header').outerHeight() -
                modal.find('.modal-footer').outerHeight()
        );
    }

    if ($.fn.modal) {
        var bigmodals = $(),
            modal;

        $(window).resize(function() {
            bigmodals.filter(':visible').each(function() {
                update($(this));
            });
        });

        $.fn.bigmodal = function(option) {
            var ret = $.fn.modal.apply(this, arguments);
            this.addClass('bigmodal');
            bigmodals = bigmodals.add(this);
            this.on('shown', function(){
                update($(this));
            });
            return ret;
        };
    }
    else {
        $.fn.bigmodal = function(option){
            return this;
        };
    }
}(window.jQuery));


var scaleImage = function(){
    var doScale = function(){

        var $frame = $(this);
        if (!$($frame.parent()).is(":visible")) return;
        var image = new Image();
        image.src = $frame.attr('src');

        var imageWidth = image.width;
        var imageHeight = image.height;
        var imageRatio = image.width / image.height;

        var widthLimit = $frame.parent().width();
        var heightLimit = $frame.parent().height();

        console.log(widthLimit);

        var imageFitsHorizontally = imageWidth <= widthLimit;
        var doesFit = imageFitsHorizontally && imageHeight <= heightLimit;
        if (doesFit){
            if (Math.abs($frame.width() -imageWidth) > 1) $frame.width(imageWidth);
            if (Math.abs($frame.height() -imageHeight) > 1) $frame.height(imageHeight);
            return;
        }

        if (!imageFitsHorizontally){
            var adjustedWidth = Math.min(widthLimit, (heightLimit * imageRatio));
            if (Math.abs($frame.width() -adjustedWidth) > 1)$frame.width(adjustedWidth);
            if (Math.abs($frame.height() -(adjustedWidth / imageRatio)) > 1)$frame.height(adjustedWidth / imageRatio);
        }

        var imageFitsVertically = $frame.height <= heightLimit;
        if(!imageFitsVertically){
            if (Math.abs($frame.height() -heightLimit) > 1)$frame.height(heightLimit);
            if (Math.abs($frame.width() -(heightLimit * imageRatio)) > 1)$frame.width(heightLimit * imageRatio);
        }
    };

    setTimeout(function(that){return function() {  doScale.apply(that); }}(this), 2000);

};

YUI().use('uploader', function(Y) {
	var uploader = new Y.Uploader({
		width : "300px",
		height : "40px"
	}).render('body');

	uploader.set("multipleFiles", true);
	uploader.set("dragAndDropArea", "body");
    uploader.notifications = {};


    uploader.on('uploadcomplete', function(event){
        var filename = event.file.get('name');
        uploader.notifications[filename].setText('<b>Upload complete</b> [<i>' + filename + '</i>]');
        uploader.notifications[filename].setType('success');
        uploader.notifications[filename].setTimeout(2500);
    });


	uploader.after('fileselect', function(event) {
		_(event.fileList).each(function(file) {
            var filename = file.get('name');
            uploader.notifications[filename] = noty({text: '<b>Uploading</b> [<i>' + filename + '</i>]', type: 'information', layout: 'topRight'});
			uploader.uploadAll('private/upload');
		});
	});

});

var PhotoSummaryView = Backbone.View.extend({
    render: function() {
        return "<li class='thumbnail'><img src='photo-storage/" + this.model.get('name') + "'/></li>";
    }
});

var PhotoListView = Backbone.View.extend({
    initialize: function() {
        this.listenTo(this.model, "add", this.add);
        this.$el.empty();
    },

    add: function(photoSummary) {
        this.$el.append(new PhotoSummaryView({  model: photoSummary }).render());
    }
});

var PhotoSummary = Backbone.Model.extend({
    path: ''
});

var PhotoList = Backbone.Collection.extend({
    model: PhotoSummary
});

var untaggedPhotos = new PhotoList();
untaggedPhotos.url = '/private/photoGallery/untagged';
var untaggedPhotosView = new PhotoListView({el: 'ul#untagged', model: untaggedPhotos});

untaggedPhotos.fetch();
untaggedPhotos.trigger('change');

setInterval(function() {
    untaggedPhotos.fetch();
}, 1000);


$(function() {
	$("li.thumbnail img").livequery(function(){
		scaleImage.apply(this);
	});
});

$("#photo-modal").find("#close").click(function() {
    $("#photo-modal").modal('hide');
});

var detailsEditor = ace.edit("photo-tags-editor");

$(".thumbnail").live("click", function() {
    $("#photo-modal").bigmodal();
    var src = $(this).find("img").attr('src');
    var img = $("#big-photo");
    img.attr('src', src);
    scaleImage.apply(img);
});


