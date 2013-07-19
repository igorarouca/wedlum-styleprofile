var wedlum = wedlum || {};

wedlum.ColorProfiler = function() {};

wedlum.ColorProfiler.prototype = {
	nextStep: function(profile, success) {
		$.ajax({ url: "/private/style-profile/color/next-step", data: {profile: JSON.stringify(profile)}, dataType: 'json', success: success });
	}
}
