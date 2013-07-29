module( "Style profile survey test using ui" );

// Single Color Swatch Sessions
var firstSingleColorSession = wedlum.styleprofile.survey.script[0];
var secondSingleColorSession = wedlum.styleprofile.survey.script[1];
var thirdSingleColorSession = wedlum.styleprofile.survey.script[2];

// Mini Palette Swatch Sessions
var firstMiniPaletteSession = wedlum.styleprofile.survey.script[3];


asyncTest( "On welcome page, 1st single-color session", function() {
	expect(1);

    SurveyUser.openWelcomePage();
    SurveyUser.waitForSession(firstSingleColorSession, function() {
        equal(true, true);
        start();
    });
});

var SurveyUser = {
    openWelcomePage: function(){
        $("#fixture-frame").attr("src", "/");
    },
    waitForSession: function(session, callback){

        var interval = setInterval(function(){
           var html = $("#fixture-frame").contents().find("html").html();
           if (_(session.data).every(function(e) {
               return html.indexOf(e) >= 0;
           })){
               clearInterval(interval);
               callback();
           }
        }, 1000);
        console.log($("#fixture-frame").html());
    }
};