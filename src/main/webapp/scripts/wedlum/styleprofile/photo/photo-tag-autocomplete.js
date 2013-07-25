var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.photo = wedlum.styleprofile.photo || {};

wedlum.styleprofile.photo.TagAutocomplete = Backbone.Model.extend({
    urlRoot: 'styleprofile/photo/tagautocomplete',

    isBranch: function(scope) {
        var validScopes = _(this.attributes).keys();
        return _(validScopes).any(function (validScope) {
            return (validScope == scope);
        });
    }
});

wedlum.styleprofile.photo.tagAutocomplete = new wedlum.styleprofile.photo.TagAutocomplete();
