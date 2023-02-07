$('#logout').on('click', function () {
			$('.logoutFrm').submit();
		})

		$("#leftside-navigation .sub-menu > a").click(function(e) {
  $("#leftside-navigation div ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
  e.stopPropagation()
})

$(".sidebar-list .sidebar-list-item > a").click(function(e) {
	console.log('확인')
	$(".sidebar-list .sidebar-list-item ul").slideUp(), $(this).next().is(":visible") || $(this).next().slideDown(),
	e.stopPropagation()
  })