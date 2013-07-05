var PhotoSummary = Backbone.Model.extend({
    path: ''
});

var PhotoSummaryView = Backbone.View.extend({
    render: function() {
        return "<li class='thumbnail'><img  data-photo-id='" + this.model.id + "' src='photo-storage/" + this.model.get('name') + "'/></li>";
    }
});

var PhotoList = Backbone.Collection.extend({
    model: PhotoSummary,
    current: new Backbone.Model({id: null})
});

var PhotoListView = Backbone.View.extend({

    events: {
        "click .thumbnail img" : "openPhotoDetail"
    },

    openPhotoDetail: function() {
        this.model.current.set("id", $(arguments[0].target).data('photo-id'));
        this.model.current.trigger('change');
    },

    initialize: function() {
        this.listenTo(this.model, "add", this.add);
        this.$el.empty();
    },

    add: function(photoSummary) {
        this.$el.append(new PhotoSummaryView({  model: photoSummary }).render());
    }
});

untaggedPhotos = new PhotoList();
untaggedPhotos.url = '/private/photoGallery/untagged';
untaggedPhotosView = new PhotoListView({el: 'ul#untagged', model: untaggedPhotos});

untaggedPhotos.fetch();
untaggedPhotos.trigger('change');

photoDetail = new PhotoDetail();
photoDetail.listenTo(untaggedPhotos.current, "change", function() {
    photoDetail.set("id", untaggedPhotos.current.get("id"));
    photoDetail.fetch();
    photoDetail.trigger("change");
});
photoDetailView = new PhotoDetailView({ model: photoDetail, el: "#photo-modal" });

//setInterval(function() {
//    untaggedPhotos.fetch();
//}, 1000);

$("#photo-modal #close").click(function() {
    $("#photo-modal").modal('hide');
});
