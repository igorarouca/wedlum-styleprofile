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


        $("[data-suggestion=true]").live("click", function(e) {
            var selected = $(this).html();
            if (wedlum.photo.tagAutocomplete.isBranch(that.scope))
                selected += ":";
            that.detailsEditor.insert(selected);
            $("#autocomplete").offset({top: -10000});
            that.detailsEditor.focus();
            e.preventDefault();
        });

        wedlum.photo.keybindings.Autocomplete = function() {
            $(function(){
                var suggestions = wedlum.photo.tagAutocomplete.get(that.scope);
                if (!suggestions){
                    wedlum.notifier.warning("There are no suggestions for path: " + that.scope);
                    return;
                }
                var $autocomplete = $("#autocomplete");
                $autocomplete.offset($(".ace_cursor").offset());
                $autocomplete.html(_(suggestions).map(function(suggestion) {
                    return "<a href='.' data-suggestion=true>" + suggestion + "</a>";
                }).join("</br>"));
            });
        };

        this.detailsEditor.on("changeSelection", function() {
            var line = that.detailsEditor.getCursorPosition().row;
            that.scope = "Root" + new wedlum.photo.TagModelParser().pathGivenLine(
                that.detailsEditor.getValue(), line);
            that.$el.find("#scopePhotoDetail").html(
                that.scope);
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
        wedlum.photo.tagAutocomplete.fetch();
        var src = "photo-storage/" + this.model.id;
        var img = $("#big-photo");
        img.attr('src', src);

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
