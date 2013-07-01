afterOneSecond = function(callback){
    setTimeout(callback, 1000);
}

var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};

var PhotoDetail = Backbone.Model.extend({
    urlRoot: "private/photoDetail"
});

var PhotoDetailView = Backbone.View.extend({
    initialize: function() {
        this.detailsEditor = ace.edit("photo-tags-editor");
        this.detailsEditor.getSession().setMode("ace/mode/yaml");
        this.detailsEditor.getSession().setTabSize(3);
        var that = this;
        this.listenTo(this.model, "change", this.update);
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
