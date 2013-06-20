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

var PhotoGalleryView = Backbone.View.extend({
	el: "#photo-gallery",

	events: {
		"click li" : "photoClicked"
	},

	photoClicked: function() {
		this.$el.hide();
		this.photoEditorView.$el.show();
	},

	render: function() {
		return this;
	}
});

var PhotoListView = Backbone.View.extend({
    initialize: function() {
        console.log('will listen to ' + this.model);
        this.listenTo(this.model, "change reset add remove", this.render);
    },
    render: function(){
        console.log('will render');
        var that = this;
        var html = "";
        $(this.model.models).each(function(){
            html += "<li class='thumbnail'><img src='photo-storage/" + this.get('name') + "'></li>";
        });
        that.$el.html(html);
    }
});

var PhotoEditorView = Backbone.View.extend({
	el: "#photo-editor",

	events: {
		"click #save-button" : "save"
	},

	returnToGallery: function() {
		this.$el.hide();
		this.photoGalleryView.$el.show();
	},

	save: function() {
		var that = this;
		$.ajax({ url: "photoGallery.html", complete: function() {
			that.returnToGallery();
		}});		
	},

	render: function() {
		return this;
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
    var $frame = $(this);
    var image = new Image()
    image.src = $frame.attr('src');

    var imageWidth = image.width;
    var imageHeight = image.height;
    var imageRatio = image.width / image.height;

    var frameWidth = $frame.parent().width();
    var frameHeight = $frame.parent().height();

    var imageFitsHorizontally = imageWidth <= frameWidth;
    var doesFit = imageFitsHorizontally && imageHeight <= frameHeight;
    if (doesFit)
        return


    if (!imageFitsHorizontally){
        var adjustedWidth = Math.min(frameWidth, (frameHeight * imageRatio));
        $frame.width(adjustedWidth -10);
        $frame.height(adjustedWidth / imageRatio);
    }
});


$(function() {
	var photoEditorView = new PhotoEditorView();
	var photoGalleryView = new PhotoGalleryView();

	photoGalleryView.render();
	photoEditorView.$el.hide();

	photoEditorView.photoGalleryView = photoGalleryView;
	photoGalleryView.photoEditorView = photoEditorView;
	
});
