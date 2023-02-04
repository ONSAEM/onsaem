package com.onsaem.web.course.service;

import java.util.List;
import java.util.Map;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.PaymentVO;

public interface BookingService {

	// 예약 전체조회
	public Map<String, Object> getBookingList(BookingVO vo, Paging paging);

	// 예약 단건조회
	public BookingVO getBooking(BookingVO vo);

	// 예약 갯수 조회
	public int BookingCount(BookingVO vo);

	// 예약 등록
	public BookingVO insertBooking(BookingVO vo);

	// 예약 수정
	public boolean updateBooking(BookingVO vo);

	// 결제조회
	public PaymentVO getPayment(PaymentVO vo);

	// 결제 등록
	public int insertPayment(PaymentVO vo);

	// 결제 수정
	public int updatePayment(PaymentVO vo);

	// 포인트 부여
	public int updatePoint(PaymentVO vo);

	// 포인트 조회
	public Integer getPoint(String MemberId);

	// 강사 예약 전체조회
	public List<BookingVO> getMyClassBookingList(BookingVO vo);

}
