module( "Parsing tag model" );

var template =
"Photo\n\
   Description\n\
       Photographer\n\
           John Smith\n\
   Tags\n\
       Color\n\
           Blue\n\
           Green";

var expectedPreProcessed =
"\nPhoto\n\
#INDENT{   Description\n\
#INDENT{       Photographer\n\
#INDENT{           John Smith\n\
#DEDENT}#DEDENT}   Tags\n\
#INDENT{       Color\n\
#INDENT{          Blue\n\
           Green#DEDENT}#DEDENT}#DEDENT}";

test( "It inserts INDENT and DEDENT tokens", function() {
    var subject = new wedlum.tag.TagModelParser();
    var actual = subject.preProcess(template);

    equal( actual, expectedPreProcessed );
});