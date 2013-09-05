module( "Style profile survey test using ui" );

// Single Color Swatch Sessions
var firstSingleColorSession = wedlum.styleprofile.survey.script[0];
var secondSingleColorSession = wedlum.styleprofile.survey.script[1];
var thirdSingleColorSession = wedlum.styleprofile.survey.script[2];

// Mini Palette Sessions
var firstMiniPaletteSession = wedlum.styleprofile.survey.script[3];
var secondMiniPaletteSession = wedlum.styleprofile.survey.script[4];

// Palette Session
var firstPaletteSession = wedlum.styleprofile.survey.script[5];

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
        SurveyUser.like("000M.png", "015M.png", "035M.png", "045M.png");
        SurveyUser.waitForSession(secondSingleColorSession, function() {
            equal(true, true);
            start();
        });
    });
});

asyncTest("Complete Survey", function() {
    expect(1);

    SurveyUser.openWelcomePage();

    SurveyUser.waitForSession(firstSingleColorSession, function() {
    SurveyUser.like("000M.png", "015M.png", "035M.png", "045M.png");

    SurveyUser.waitForSession(secondSingleColorSession, function() {
    SurveyUser.like("000S.png", "000N.png", "035S.png", "035N.png");

    SurveyUser.waitForSession(thirdSingleColorSession, function() {
    SurveyUser.like("000L.png", "000D.png", "035L.png", "035D.png");

    SurveyUser.waitForSession(firstMiniPaletteSession, function() {
    SurveyUser.like("000M_A.png", "015M_A.png", "035M_A.png", "045M_A.png");

    SurveyUser.waitForSession(secondMiniPaletteSession, function() {
    SurveyUser.like("000M_C.png", "015M_C.png", "035M_C.png", "045M_C.png");

    SurveyUser.waitForSession(firstPaletteSession, function() {
    equal(true, true);
    start();

    }); }); }); }); }); });
});

var SurveyUser = {
    openWelcomePage: function(){
        BaseUser.navigate("/");
    },

    waitForSession: function(session, callback) {
        var interval = setInterval(function() {
           var html = $("#fixture-frame").contents().find("html").html();
           if (!html) return;

           if (session.data.length != $("#fixture-frame").contents().find("#photo-group-list li").length) return;

           if (($("#fixture-frame").contents().find("[id*='central-photos']").attr("class") == 'photo-list ' + session.name) && _(session.data).every(function(e) {
               var isToken = e.indexOf("{") == 0;
               if (isToken) return true;

               return html.indexOf(e) >= 0;
           })) {
               clearInterval(interval);
               callback();
           }
        }, 1000);
    },

    like: function() {
        _(arguments).each(function(el) {
            $("#fixture-frame").contents().find("img[src*='" + el + "']").parent().find("a")[0].click()
        });
    }
};