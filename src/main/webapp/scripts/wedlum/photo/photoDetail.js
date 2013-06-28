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
    urlRoot: "private/photoDetail",

    initialize: function() {
        this.set("tagModel", wedlum.photo.template);
    }
});

var PhotoDetailView = Backbone.View.extend({
    initialize: function() {
        this.detailsEditor = ace.edit("photo-tags-editor");
        this.detailsEditor.getSession().setMode("ace/mode/yaml");
        this.detailsEditor.getSession().setTabSize(3);
        this.listenTo(this.model, "change", this.update);
    },

    update: function() {
        this.detailsEditor.getSession().setValue(this.model.get("tagModel"));
        $("#photo-modal").bigmodal();
        var src = "photo-storage/" + this.model.id;
        var img = $("#big-photo");
        img.attr('src', src);
    }
});
