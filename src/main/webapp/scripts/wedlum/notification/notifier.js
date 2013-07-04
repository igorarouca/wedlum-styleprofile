var wedlum = wedlum||{};
wedlum.notifier = {
    notifySuccess: function(text){
        var result = noty({text: text, type: 'success', layout: 'topRight'});
        result.setTimeout(2500);
        return result;
    },

    notifyFailure: function(text){
        var result = noty({text: text, type: 'error', layout: 'topRight'});
        return result;
    },

    notifyWarning: function(text){
        var result = noty({text: text, type: 'warning', layout: 'topRight'});
        result.setTimeout(2500);
        return result;
    }
}