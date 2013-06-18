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
			uploader.upload(file, 'upload');
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

var Photo = Backbone.Model.extend({

});

var PhotoGallery = Backbone.Collection.extend({
    url: '/photoGallery',
    model: PhotoModel
});



$(function() {
	var photoEditorView = new PhotoEditorView();
	var photoGalleryView = new PhotoGalleryView();

	photoGalleryView.render();
	photoEditorView.$el.hide();

	photoEditorView.photoGalleryView = photoGalleryView;
	photoGalleryView.photoEditorView = photoEditorView;
	
});


function UntaggedController($scope) {
    $scope.untaggedcount = 42;
}
