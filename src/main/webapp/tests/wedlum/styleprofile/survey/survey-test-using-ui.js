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

asyncTest( "1st session completed, waiting for the 2nd", function() {
    expect(1);

    SurveyUser.openWelcomePage();
    SurveyUser.waitForSession(firstSingleColorSession, function() {
        SurveyUser.like("000M.png");
        SurveyUser.like("015M.png");
        SurveyUser.like("035M.png");
        SurveyUser.like("045M.png");
        SurveyUser.waitForSession(secondSingleColorSession, function() {
            equal(true, true);
            start();
        });
    });
});

var SurveyUser = {
    openWelcomePage: function(){
        $("#fixture-frame").attr("src", "/");
    },

    waitForSession: function(session, callback) {
        var interval = setInterval(function() {
           var html = $("#fixture-frame").contents().find("html").html();
           if (!html) return;

           if (session.data.length != $("#fixture-frame").contents().find("#photo-group-list li").length) return;

           if (($("#fixture-frame").contents().find("[id*='central-photos']").attr("class") == 'photo-list ' + session.name) && _(session.data).every(function(e) {
               return html.indexOf(e) >= 0;
           })) {
               clearInterval(interval);
               callback();
           }
        }, 1000);
    },

    like: function(swatch){
        $("#fixture-frame").contents().find("img[src*='" + swatch + "']").parent().find("a")[0].click()
    }
};