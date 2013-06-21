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
    var $frame = $(this);
    var image = new Image()
    image.src = $frame.attr('src');

    var imageWidth = image.width;
    var imageHeight = image.height;
    var imageRatio = image.width / image.height;

    var frameWidth = $frame.parent().width();
    var frameHeight = $frame.parent().height();

    console.debug("frameWidth: " + frameWidth);

    var imageFitsHorizontally = imageWidth <= frameWidth;
    var doesFit = imageFitsHorizontally && imageHeight <= frameHeight;
    if (doesFit){
        $frame.width(imageWidth);
        $frame.height(imageHeight);
        return;
    }

    if (!imageFitsHorizontally){
        var adjustedWidth = Math.min(frameWidth, (frameHeight * imageRatio));
        $frame.width(adjustedWidth);
        $frame.height(adjustedWidth / imageRatio);
    }

    
};




/* TODO:
 * - Define (2) models for photos (gallery info vs editor info)
 * - Define collections tagged and untagged photos and respective JSON files
 * - Superview loops to iterate over collections instantiating subviews for each individual photo
 * - Pull down click event to the subview passing photo id to the editor view
*/

YUI().use('uploader', function(Y) {
	var uploader = new Y.Uploader({
		width : "300px",
		height : "40px"
	}).render('body');

	uploader.set("multipleFiles", true);
	uploader.set("dragAndDropArea", "body");

	uploader.after('fileselect', function(args) {
		_(args.fileList).each(function(file) {
			uploader.upload(file, 'private/upload');
		});
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

setInterval(function(){
    untaggedPhotos.fetch();
}, 1000);


$("li.thumbnail img").livequery(function(){
    scaleImage.apply(this);
});


$("#photo-modal #close").click(function(){
    $("#photo-modal").modal('hide');
});

$(".thumbnail").live("click", function(){
    $("#photo-modal").bigmodal();
    var src = $(this).find("img").attr('src');
    var img = $("#big-photo");
    img.attr('src', src);
    scaleImage.apply(img);
});


