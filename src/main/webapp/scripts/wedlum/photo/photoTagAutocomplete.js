var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};

wedlum.photo.AutocompleteSuggestions = Backbone.Model.extend({
    urlRoot: 'private/photoGallery/autocomplete',
    isBranch: function(scope) {
        var validScopes = _(this.attributes).keys();
        var isBranch =  _(validScopes).any(function(validScope) {
            return (validScope.indexOf(scope + '/') == 0);
        });
        return isBranch;
    }
});

wedlum.photo.autocompleteSuggestions = new wedlum.photo.AutocompleteSuggestions();