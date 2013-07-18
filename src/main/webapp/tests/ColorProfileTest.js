module( "Color profile test" );

asyncTest( "On empty profile return first session", function() {
	expect(1);

	var subject = new wedlum.ColorProfiler();
	var profile = {};
	var firstSession =
  /*1*/    "Step: {" +
  /*2*/    "	type: session" +
  /*3*/    "	session-name: colorsession#" +
  /*4*/    "	photos: [ 1.png, 2.png, 3.png, 4.png, 5.png, 6.png," +
  						 "7.png, 8.png, 9.png, 10.png, 11.png, 12.png," +
  						 "13.png, 14.png, 15.png, 16.png, 17.png, 18.png ]" +
  /*5*/    "}";


	subject.nextStep(profile, function(nextStep) {
		equal(nextStep!=null, true);
		start();		
	});

});
