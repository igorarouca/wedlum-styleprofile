var wedlum = wedlum || {};
wedlum.notifier = {
    success: function(text){
        var result = noty({text: text, type: 'success', layout: 'topRight'});
        result.setTimeout(2500);
        return result;
    },

    failure: function(text){
        var result = noty({text: text, type: 'error', layout: 'topRight'});
        return result;
    },

    warning: function(text){
        var result = noty({text: text, type: 'warning', layout: 'topRight'});
        result.setTimeout(2500);
        return result;
    }
}
