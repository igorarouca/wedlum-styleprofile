Commons = {
    waitFor: function(condition, callback){
        var interval = setInterval(function(){
            if (condition()){
                clearInterval(interval);
                callback();
            }
        }, 1000);
    }
}