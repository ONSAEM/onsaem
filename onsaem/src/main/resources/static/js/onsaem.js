$('#logout').on('click', function () {
			$('.logoutFrm').submit();
		})

		$("#leftside-navigation .sub-menu > a").click(function(e) {
  $("#leftside-navigation div ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
  e.stopPropagation()
})