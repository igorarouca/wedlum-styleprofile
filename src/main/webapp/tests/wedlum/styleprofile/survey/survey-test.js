module( "Style profile survey test" );

// Single Color Swatch Sessions
var firstSingleColorSession = wedlum.styleprofile.survey.script[0];
var secondSingleColorSession = wedlum.styleprofile.survey.script[1];
var thirdSingleColorSession = wedlum.styleprofile.survey.script[2];

// Mini Palette Swatch Sessions
var firstMiniPaletteSession = wedlum.styleprofile.survey.script[3];

asyncTest( "On empty profile return 1st single-color session", function() {
	expect(2);

    var profile = {};
	var subject = new wedlum.styleprofile.survey.Survey(profile);

	subject.nextStep(function(nextStep) {
		equal(JSON.stringify(nextStep), JSON.stringify(firstSingleColorSession));
        equal(18,nextStep.data.length);
		start();
	});
});

asyncTest( "On profile with 1st single-color session return 2nd single-color session", function() {
    expect(1);

    var profile = {};
    var subject = new wedlum.styleprofile.survey.Survey(profile);
    profile[firstSingleColorSession.name] = { likedPhotos: ["1.png", "2.png"], allPhotos: [] };

    subject.nextStep(function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(secondSingleColorSession));
        start();
    });
});

asyncTest( "On profile with favorite colors return 1st mini-palette session", function() {
    expect(1);

    var profile = {};
    var subject = new wedlum.styleprofile.survey.Survey(profile);

    profile[firstSingleColorSession.name] =  { likedPhotos: ["1a.png", "2a.png", "3a.png", "4a.png"], allPhotos: [] };
    profile[secondSingleColorSession.name] = { likedPhotos: ["1b.png", "2b.png", "3b.png", "4b.png"], allPhotos: [] };
    profile[thirdSingleColorSession.name] =  { likedPhotos: ["1c.png", "2c.png", "3c.png", "4c.png"], allPhotos: [] };

    var expected = JSON.parse(JSON.stringify(firstMiniPaletteSession));
    expected.data = [ "1a_A.png", "2a_A.png", "3a_A.png", "4a_A.png",
                      "1b_A.png", "2b_A.png", "3b_A.png", "4b_A.png",
                      "1c_A.png", "2c_A.png", "3c_A.png", "4c_A.png"];

    subject.nextStep(function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(expected));
        start();
    });
});
