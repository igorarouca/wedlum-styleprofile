var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};


wedlum.photo.template =
    "Photo:\n\
       Description:\n\
          Photographer:\n\
             Drue Carr\n\
       Tags:\n\
           Color:\n\
               - Red\n\
               - Green\n\
               - Blue";

var PhotoDetail = Backbone.Model.extend({
    initialize: function() {
        this.set("tagModel", wedlum.photo.template);
    }
});

var PhotoDetailView = Backbone.View.extend({
    initialize: function() {
        this.detailsEditor = ace.edit("photo-tags-editor");
        this.detailsEditor.getSession().setMode("ace/mode/yaml");
        this.detailsEditor.getSession().setTabSize(3);
        this.listenTo(this.model, "change", this.updateTagModelEditor);
        this.updateTagModelEditor();
        this.options.selectedPhoto.on('change', this.selectedPhotoChanged, this);
    },

    updateTagModelEditor: function() {
        this.detailsEditor.getSession().setValue(this.model.get("tagModel"));
    },

    selectedPhotoChanged: function(){
        $("#photo-modal").bigmodal();
        var src = "photo-storage/" + this.options.selectedPhoto.get('id');
        var img = $("#big-photo");
        img.attr('src', src);
    }
});
