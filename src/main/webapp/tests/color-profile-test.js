module( "Color profile test" );

var firstSession = wedlum.testScript[0];
var secondSession = wedlum.testScript[1];
var thirdSession = wedlum.testScript[2];

var firstMiniPaletteSession = wedlum.testScript[3];

asyncTest( "On empty profile return first session", function() {
	expect(1);

	var subject = new wedlum.ColorProfiler();
	var profile = {};

	subject.nextStep(profile, function(nextStep) {
		equal(JSON.stringify(nextStep), JSON.stringify(firstSession));
		start();
	});
});

asyncTest( "On profile with first session return second session", function() {
    expect(1);

    var subject = new wedlum.ColorProfiler();
    var profile = {};
    profile[firstSession.name] = ["1.png", "2.png"];

    subject.nextStep(profile, function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(secondSession));
        start();
    });
});

asyncTest( "On profile with favorite colors return 1st mini-palette session", function() {
    expect(1);

    var subject = new wedlum.ColorProfiler();
    var profile = {};

    profile[firstSession.name] = ["1a.png", "2a.png", "3a.png", "4a.png"];
    profile[secondSession.name] = ["1b.png", "2b.png", "3b.png", "4b.png"];
    profile[thirdSession.name] = ["1c.png", "2c.png", "3c.png", "4c.png"];

    var expected = JSON.parse(JSON.stringify(firstMiniPaletteSession));
    expected.data = [ "1a_bold.png", "2a_bold.png", "3a_bold.png", "4a_bold.png",
                      "1b_bold.png", "2b_bold.png", "3b_bold.png", "4b_bold.png",
                      "1c_bold.png", "2c_bold.png", "3c_bold.png", "4c_bold.png"];

    subject.nextStep(profile, function(nextStep) {
        equal(JSON.stringify(nextStep), JSON.stringify(expected));
        start();
    });
});
