var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.photo = wedlum.styleprofile.photo || {};

$.ctrl = function(key, callback, args) {
    $(document)
        .keydown(function(e) {
            if (!args) args = [];

            if (e.keyCode == key && (e.ctrlKey || e.metaKey)) {
                callback.apply(this, args);
                return false;
            }
            return true;
        });
};

$(function() {
    wedlum.styleprofile.photo.keybindings.init();
});

wedlum.styleprofile.photo.keybindings = {
    init: function() {
        var space = '32';
        var that = this;
        $.ctrl(space, function() {
            if (that.Autocomplete) that.Autocomplete();
            return false;
        });
    }
};
