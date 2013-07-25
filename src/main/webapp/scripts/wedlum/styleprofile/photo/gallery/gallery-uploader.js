var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.photo = wedlum.styleprofile.photo || {};
wedlum.styleprofile.photo.gallery = wedlum.styleprofile.photo.gallery || {};

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
            uploader.uploadAll('styleprofile/photo/upload');
        });
    });

});

