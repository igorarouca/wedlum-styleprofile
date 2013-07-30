module( "Style profile survey test" );

// Single Color Swatch Sessions
var firstSingleColorSession = wedlum.styleprofile.survey.script[0];
var secondSingleColorSession = wedlum.styleprofile.survey.script[1];
var thirdSingleColorSession = wedlum.styleprofile.survey.script[2];

// Mini Palette Swatch Sessions
var firstMiniPaletteSession = wedlum.styleprofile.survey.script[3];
var secondMiniPaletteSession = wedlum.styleprofile.survey.script[4];

asyncTest( "On empty profile return 1st single-color session", function() {
	expect(1);

	var subject = new wedlum.styleprofile.survey.Survey();
	var profile = {};

	subject.nextStep(profile, function(nextStep) {
		equal(JSON.stringify(nextStep), JSON.stringify(firstSingleColorSession));
		start();
	});
});

asyncTest( "On profile with 1st single-color session return 2nd single-color session", function() {
    expect(1);

    var subject = new wedlum.styleprofile.survey.Survey();
    var profile = {};
    profile[firstSingleColorSession.name] = ["1.png", "2.png"];

    subject.nextStep(profile, function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(secondSingleColorSession));
        start();
    });
});

asyncTest( "On profile with favorite colors return 1st mini-palette session", function() {
    expect(1);

    var subject = new wedlum.styleprofile.survey.Survey();
    var profile = {};

    profile[firstSingleColorSession.name] =  ["1a.png", "2a.png", "3a.png", "4a.png"];
    profile[secondSingleColorSession.name] = ["1b.png", "2b.png", "3b.png", "4b.png"];
    profile[thirdSingleColorSession.name] =  ["1c.png", "2c.png", "3c.png", "4c.png"];

    var expected = JSON.parse(JSON.stringify(firstMiniPaletteSession));
    expected.data = [ "1a_A.png", "2a_A.png", "3a_A.png", "4a_A.png",
                      "1b_A.png", "2b_A.png", "3b_A.png", "4b_A.png",
                      "1c_A.png", "2c_A.png", "3c_A.png", "4c_A.png"];

    subject.nextStep(profile, function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(expected));
        start();
    });
});
