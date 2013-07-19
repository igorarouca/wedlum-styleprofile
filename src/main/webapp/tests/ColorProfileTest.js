module( "Color profile test" );

var firstSession = wedlum.testScript[0];
var secondSession = wedlum.testScript[1];

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
