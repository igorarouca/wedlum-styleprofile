var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};

wedlum.photo.TagAutocomplete = Backbone.Model.extend({
    urlRoot: 'private/photoGallery/autocomplete',

    isBranch: function(scope) {
        var validScopes = _(this.attributes).keys();
        return _(validScopes).any(function (validScope) {
            return (validScope == scope);
        });
    }
});

wedlum.photo.tagAutocomplete = new wedlum.photo.TagAutocomplete();