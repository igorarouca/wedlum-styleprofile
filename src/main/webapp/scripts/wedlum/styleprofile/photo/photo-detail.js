var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.photo = wedlum.styleprofile.photo || {};

wedlum.styleprofile.photo.template =
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
    urlRoot: "styleprofile/photo/detail"
});

var PhotoDetailView = Backbone.View.extend({
    initialize: function() {
        var that = this;
        this.detailsEditor = ace.edit("photo-tags-editor");
        this.detailsEditor.getSession().setMode("ace/mode/yaml");
        this.detailsEditor.getSession().setTabSize(3);
        this.listenTo(this.model, "change", this.update);

        codiad.autocomplete._getEditor = function(){
            return that.detailsEditor;
        };

        codiad.autocomplete.getSuggestions = function(){
            var result = {};
            _(wedlum.styleprofile.photo.tagAutocomplete.get(that.scope)).each(function(suggestion) {
                result[suggestion + (wedlum.styleprofile.photo.tagAutocomplete.isBranch(that.scope + "/" + suggestion) ?":":"") + " "] = 0;
            });
            return result;
        }

        wedlum.styleprofile.photo.keybindings.Autocomplete = function() {
            $(function(){
                codiad.autocomplete.suggest();
            });
        };

        this.detailsEditor.on("changeSelection", function() {
            var line = that.detailsEditor.getCursorPosition().row;
            that.scope = "Root" + new wedlum.styleprofile.photo.TagModelParser().pathGivenLine(that.detailsEditor.getValue(), line);
            that.$el.find("#scopePhotoDetail").html(that.scope);
        });
    },

    events: {
        "click #savePhotoDetail" : "save",
        "click #cancelPhotoDetail" : "hide",
        "click #deletePhotoDetail" : "delete"
    },

    update: function() {
        console.debug("Update " + this.model.id);
        this.detailsEditor.getSession().setValue(this.model.get("metadata"));
        $("#photo-modal").bigmodal();
        wedlum.styleprofile.photo.tagAutocomplete.fetch();
        var src = "photo-storage/" + this.model.id;
        var img = $("#big-photo");
        img.attr('src', src);

    },

    delete: function(){
        var confirmation = window.prompt("Are you sure you want to delete this picture? (type yes to confirm)","no");
        if (confirmation == 'yes'){
            this.model.destroy();
            var that = this;
            afterOneSecond(function() {
                that.hide();
            });
        }
    },

    save: function() {
        this.model.set('metadata', this.detailsEditor.getValue());
        var saveButton = this.$el.find("#savePhotoDetail");
        saveButton.html("Saving...");
        var that = this;

        that.model.save({}, {
            success: function() {
                wedlum.notifier.success("Photo details saved for " + that.model.id);
                afterOneSecond(function() {
                    saveButton.html("Save");
                    that.hide();
            });},
            error: function() {
                wedlum.notifier.failure("Error saving " + that.model.id);
                afterOneSecond(function() {
                    saveButton.html("Save");
            });
        }});
    },

    hide: function() {
        this.$el.bigmodal('hide');
    }
});
