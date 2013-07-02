module( "Tag model parser" );



test( "parseTagModel", function() {
    var subject = new wedlum.tag.TagModelParser();
    actual = subject.parse(wedlum.photo.template);
    equal( JSON.stringify(actual), "{\"Photo\":" +
        "{\"Description\":{\"Photographer\":\"Drue Carr\"}," +
        "\"Tags\":{\"Color\":[\"Red\",\"Green\",\"Blue\"]}}}");
});

test ( "getPathGivenLine", function() {
    var tag =
    /*1*/    "Photo:\n" +
    /*2*/    "   Tags:\n" +
    /*3*/    "       Color:\n" +
    /*4*/    "           - blue\n" +
    /*5*/    "           - indigo";

    var subject = new wedlum.tag.TagModelParser();
    equal("/Photo", subject.pathGivenLine(tag, 1));
    equal("/Photo/Tags", subject.pathGivenLine(tag, 2));
    equal("/Photo/Tags/Color", subject.pathGivenLine(tag, 3));
    equal("/Photo/Tags/Color", subject.pathGivenLine(tag, 4));

});
