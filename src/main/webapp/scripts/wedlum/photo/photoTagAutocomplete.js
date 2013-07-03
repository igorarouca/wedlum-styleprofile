var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};

wedlum.photo.AutocompleteSuggestions = Backbone.Model.extend({
    urlRoot: 'private/photoGallery/autocomplete'
});

wedlum.photo.autocompleteSuggestions = new wedlum.photo.AutocompleteSuggestions();