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

    profile[firstSingleColorSession.name] = ["color1.png", "color2.png", "color3.png", "color4.png"];
    profile[secondSingleColorSession.name] = ["color19.png", "color20.png", "color21.png", "color22.png"];
    profile[thirdSingleColorSession.name] = ["color37.png", "color38.png", "color39.png", "color40.png"];

    var expected = JSON.parse(JSON.stringify(firstMiniPaletteSession));
//    expected.data = [ "1a_bold.png", "2a_bold.png", "3a_bold.png", "4a_bold.png",
//                      "1b_bold.png", "2b_bold.png", "3b_bold.png", "4b_bold.png",
//                      "1c_bold.png", "2c_bold.png", "3c_bold.png", "4c_bold.png"];

    expected.data = [ "ToDo", "ToDo", "ToDo", "ToDo",
                      "ToDo", "ToDo", "ToDo", "ToDo",
                      "ToDo", "ToDo", "ToDo", "ToDo"];

    subject.nextStep(profile, function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(expected));
        start();
    });
});
