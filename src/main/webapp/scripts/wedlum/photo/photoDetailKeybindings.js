var wedlum = wedlum||{};
wedlum.photo = wedlum.photo||{};

(function(global, $){

    $.ctrl = function(key, callback, args) {
        $(document)
            .keydown(function(e) {
                if (!args) args = [];
                if (e.keyCode == key && (e.ctrlKey || e.metaKey)) {
                    callback.apply(this, args);
                    return false;
                }
            });
    };

    $(function() {
        wedlum.photo.keybindings.init();
    });

    wedlum.photo.keybindings = {
        init: function() {
            var space = '32';
            var that = this;
            $.ctrl(space, function() {
                if (that.Autocomplete) that.Autocomplete();
                return false;
            });
        }
    };

})(this, jQuery);