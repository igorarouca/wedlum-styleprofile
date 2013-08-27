module( "Photo gallery test using ui" );

asyncTest( "test.png is available", function() {
    expect(1);

    AdminUser.openPhotoGallery();
    AdminUser.waitForPhotoInGallery("test.png", function() {
        equal(true, true);
        start();
    });
});

AdminUser = {

    openPhotoGallery: function() {
        BaseUser.navigate("/photo-gallery.html");
    },

    waitForPhotoInGallery: function(photoId, callback) {
        Commons.waitFor(function() {
            return $("#fixture-frame").contents().find("[data-photo-id='" + photoId +  "']").length > 0;
        }, callback);
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



