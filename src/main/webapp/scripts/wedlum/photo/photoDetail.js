afterOneSecond = function(callback){
    setTimeout(callback, 1000);
}

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
    urlRoot: "private/photoDetail"
});

var PhotoDetailView = Backbone.View.extend({
    initialize: function() {
        var that = this;
        this.detailsEditor = ace.edit("photo-tags-editor");
        this.detailsEditor.getSession().setMode("ace/mode/yaml");
        this.detailsEditor.getSession().setTabSize(3);
        this.listenTo(this.model, "change", this.update);
        this.detailsEditor.on("changeSelection", function(){
            var line = that.detailsEditor.getCursorPosition().row;
            that.$el.find("#scopePhotoDetail").html(
                new wedlum.tag.TagModelParser().pathGivenLine(
                    that.detailsEditor.getValue(), line));
        });
    },

    events: {
        "click #savePhotoDetail" : "save",
        "click #cancelPhotoDetail" : "hide"
    },

    update: function() {
        console.debug("Update " + this.model.id);
        this.detailsEditor.getSession().setValue(this.model.get("metadata"));
        $("#photo-modal").bigmodal();
        var src = "photo-storage/" + this.model.id;
        var img = $("#big-photo");
        img.attr('src', src);

    },

    save: function(){
        this.model.set('metadata', this.detailsEditor.getValue());
        var saveButton = this.$el.find("#savePhotoDetail");
        saveButton.html("Saving...");
        var that = this;

        that.model.save({}, {
            success: function(){
                wedlum.notifier.notifySuccess("Photo details saved for " + that.model.id);
                afterOneSecond(function() {
                    saveButton.html("Save");
                    that.hide();
            });},
            error: function(){
                wedlum.notifier.notifyFailure("Error saving " + that.model.id);
                afterOneSecond(function() {
                    saveButton.html("Save");
            });
        }});
    },

    hide: function(){
        this.$el.bigmodal('hide');
    }


});
