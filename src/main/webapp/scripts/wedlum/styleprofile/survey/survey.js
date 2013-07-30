var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.survey = wedlum.styleprofile.survey || {};

wedlum.styleprofile.survey.Survey = function() {};

wedlum.styleprofile.survey.Survey.prototype = {
	nextStep: function(profile, success) {
		$.ajax({
			type: "POST",
			url: "/styleprofile/survey/nextstep",
			data: { profile: JSON.stringify(profile) },
			dataType: 'json',
			success: success
		});
	}
}
