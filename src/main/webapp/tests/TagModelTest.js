module( "Tag model parser" );

var template =
 "Photo:\n\
    Description:\n\
    Photographer:\n\
        John Smith\n\
    Tags:\n\
        Color:\n\
            - Blue\n\
            - Green";

test( "parseTagModel", function() {
    var subject = new wedlum.tag.TagModelParser();
    actual = subject.parse(template);

    equal( JSON.stringify(actual), "{\"Photo\":{\"Description\":null,\"Photographer\":\"John Smith\",\"Tags\":{\"Color\":[\"Blue\",\"Green\"]}}}");
});