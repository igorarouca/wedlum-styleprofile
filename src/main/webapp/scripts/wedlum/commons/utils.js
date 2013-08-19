afterOneSecond = function(callback) {
    setTimeout(callback, 1000);
};

preloadImages = function(images, callback) {
    var count = images.length;
    _(images).each(function(image){
        var subject = new Image();
        subject.src = image;
        subject.onload=function(){
            count--;
            if (count == 0) callback();
        };
    });
};

(function($){
    function update(modal) {
        modal.find('.modal-body').outerHeight(
            modal.innerHeight() -
                modal.find('.modal-header').outerHeight() -
                modal.find('.modal-footer').outerHeight()
        );
    }

    if ($.fn.modal) {
        var bigmodals = $(),
            modal;

        $(window).resize(function() {
            bigmodals.filter(':visible').each(function() {
                update($(this));
            });
        });

        $.fn.bigmodal = function(option) {
            var ret = $.fn.modal.apply(this, arguments);
            this.addClass('bigmodal');
            bigmodals = bigmodals.add(this);
            this.on('shown', function(){
                update($(this));
            });
            return ret;
        };
    }
    else {
        $.fn.bigmodal = function(){
            return this;
        };
    }
} (window.jQuery));