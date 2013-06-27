module( "Tag model parser" );



test( "parseTagModel", function() {
    var subject = new wedlum.tag.TagModelParser();
    actual = subject.parse(wedlum.photo.template);
    equal( JSON.stringify(actual), "{\"Photo\":{\"Description\":null,\"Photographer\":\"John Smith\",\"Tags\":{\"Color\":[\"Blue\",\"Green\"]}}}");
});