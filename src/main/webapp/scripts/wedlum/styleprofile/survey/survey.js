var wedlum = wedlum || {};
wedlum.styleprofile = wedlum.styleprofile || {};
wedlum.styleprofile.survey = wedlum.styleprofile.survey || {};

wedlum.styleprofile.survey.Survey = function(profile) {
    if (!profile) throw "profile is required";
    this.profile = profile;
};

wedlum.styleprofile.survey.Survey.prototype = {
	nextStep: function(success) {
        var that = this;
		$.ajax({
			type: "POST",
			url: "/styleprofile/survey/nextstep",
			data: { profile: JSON.stringify(this.profile) },
			dataType: 'json',
			success: function(step) {
                success(step);
            }
		});
	}
}
