module( "Photo gallery test using ui" );

asyncTest( "test.png is available", function() {
    expect(1);

    AdminUser.openPhotoGallery();
    AdminUser.waitForPhotoInGallery("test.png", function() {
        equal(true, true);
        start();
    });
});

asyncTest( "edit test.png metadata", function() {
    expect(1);

    AdminUser.openPhotoGallery();
    AdminUser.setPhotoMetadata(
        "test.png",
        "Photo:\n" +
        "   Tags:\n" +
        "       Colors:\n" +
        "           - 042",
        function() {
            AdminUser.assertPhotoMetadata(
                "test.png",
                "Photo:\n" +
                "   Tags:\n" +
                "       Colors:\n" +
                "           - 042",
                function() {
                    start();
                }
            );
    });
});

AdminUser = {

    openPhotoGallery: function() {
        BaseUser.navigate("/photo-gallery.html");
    },

    findPhoto: function (photoId) {
        return $("#fixture-frame").contents().find("[data-photo-id='" + photoId + "']");
    },

    findPhotoEditor: function () {
        return $("#fixture-frame").contents().find(".ace_text-input");
    },

    waitForPhotoInGallery: function(photoId, callback) {
        var that = this;
        Commons.waitFor(function() {
            return that.findPhoto(photoId).length > 0;
        }, callback);
    },

    waitForPhotoEditor: function(callback) {
        var that = this;
        Commons.waitFor(function() {
            if (that.findPhotoEditor().length > 0) {
                return $("#fixture-frame")[0].contentWindow.eval(
                    'ace.edit("photo-tags-editor").getSession().getValue() != ""'
                );
            }
            return false;
        }, callback);
    },

    setPhotoMetadata: function(photoId, metadata, callback) {
        var that = this;
        this.waitForPhotoInGallery(photoId, function() {
            that.findPhoto(photoId).click();
            that.waitForPhotoEditor(function() {
                $("#fixture-frame")[0].contentWindow.eval(
                    'ace.edit("photo-tags-editor").getSession().setValue(' + JSON.stringify(metadata) + ')'
                );
                $("#fixture-frame").contents().find("#savePhotoDetail").click();
            });
        });
    },

    assertPhotoMetadata: function(photoId, metadata, callback) {

    }

};

//Edit Metadata (test.png)
//Save
//Reload
//Check
//Delete
//Will be removed from gallery


//LATER
//--Upload a new photo
//Check template is correct



