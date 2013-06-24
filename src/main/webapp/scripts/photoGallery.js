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
    $(this).load(function(){
    var $frame = $(this);
    var image = new Image();
    image.src = $frame.attr('src');

    var imageWidth = image.width;
    var imageHeight = image.height;
    var imageRatio = image.width / image.height;

    var widthLimit = $frame.parent().width();
    var heightLimit = $frame.parent().height();

    var imageFitsHorizontally = imageWidth <= widthLimit;
    var doesFit = imageFitsHorizontally && imageHeight <= heightLimit;
    if (doesFit){
        $frame.width(imageWidth);
        $frame.height(imageHeight);
        return;
    }

    if (!imageFitsHorizontally){
        var adjustedWidth = Math.min(widthLimit, (heightLimit * imageRatio));
        $frame.width(adjustedWidth);
        $frame.height(adjustedWidth / imageRatio);
    }

    var imageFitsVertically = $frame.height <= heightLimit;
    if(!imageFitsVertically){
        $frame.height(heightLimit);
        $frame.width(heightLimit * imageRatio);
    }
    });
    $(this).load();

};

YUI().use('uploader', function(Y) {
	var uploader = new Y.Uploader({
		width : "300px",
		height : "40px"
	}).render('body');

	uploader.set("multipleFiles", true);
	uploader.set("dragAndDropArea", "body");
    uploader.notifications = {};
	uploader.after('fileselect', function(event) {
		_(event.fileList).each(function(file) {
            var filename = file.get('name');
            uploader.notifications[filename] = noty({text: '<b>Uploading</b> [<i>' + filename + '</i>]', type: 'information', layout: 'topRight'});
			UU = uploader.upload(file, 'private/upload');
		});
	});

    uploader.on('uploadcomplete', function(event){
        var filename = event.file.get('name');
        uploader.notifications[filename].setText('<b>Upload complete</b> [<i>' + filename + '</i>]');
        uploader.notifications[filename].setType('success');
        uploader.notifications[filename].setTimeout(2000);
    });
});

var PhotoListView = Backbone.View.extend({
    initialize: function() {
        this.listenTo(this.model, "change reset add remove", this.render);
    },
    render: function(){
        var that = this;
        var html = "";
        $(this.model.models).each(function(){
            html += "<li class='thumbnail'><img src='photo-storage/" + this.get('name') + "'/></li>";
        });
        that.$el.html(html);
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
		$(this).resize(function() {
			scaleImage.apply(this);
		});

		scaleImage.apply(this);
	});
});

$("#photo-modal #close").click(function() {
    $("#photo-modal").modal('hide');
});

$("#photo-modal").click(function() {
    $("#photo-modal img").flippy({
        color_target: "white",
        direction: "left",
        duration: "750",
        verso: "<div contenteditable='true'>Tags:</div>"
    });
});

$(".thumbnail").live("click", function() {
    $("#photo-modal").bigmodal();
    var src = $(this).find("img").attr('src');
    var img = $("#big-photo");
    img.attr('src', src);
    img.resize(function() {
        scaleImage.apply(this);
    });

    scaleImage.apply(img);
});


