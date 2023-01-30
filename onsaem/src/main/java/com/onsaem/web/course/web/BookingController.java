package com.onsaem.web.course.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.course.service.BookingService;
import com.onsaem.web.course.service.BookingVO;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class BookingController {
	
	@Autowired
	ClassService classService;
	
	@Autowired
	BookingService bookingService;
	
	// 예약결제 페이지 이동
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String booking(ClassVO vo, Model model, Authentication authentication) {
		vo.setCNo("C003");
		model.addAttribute("class", classService.getClass(vo));
		model.addAttribute("people", 2);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		model.addAttribute("point", bookingService.getPoint(userDetails.getUsername()));
		return "content/course/booking";
	}
	
	// 예약, 결제 등록
	@RequestMapping(value = "/insertBooking", method = RequestMethod.POST)
	@ResponseBody
	public BookingVO insertBooking(BookingVO vo, PaymentVO pvo) {
		if(bookingService.insertPayment(pvo)>0) {
			bookingService.updatePoint(pvo);
			return bookingService.insertBooking(vo);
		}else {
			return null;
		}
	}

	// 예약완료 페이지 이동
	@RequestMapping(value = "/BookingCOM", method = RequestMethod.GET)
	public String BookingCOM(BookingVO booking, Model model, Authentication authentication) {
		model.addAttribute("booking", bookingService.getBooking(booking));
		PaymentVO pvo = new PaymentVO();
		pvo.setPaymentId(booking.getPaymentId());
		model.addAttribute("payment", bookingService.getPayment(pvo));
		model.addAttribute("point", bookingService.getPoint(booking.getBookingId()));
		return "content/course/BookingCOM";
	}
}
