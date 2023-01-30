package com.onsaem.web.course.mapper;

import java.util.List;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.course.service.BookingVO;

public interface BookingMapper {
	// 문의 전체조회
	public List<BookingVO> getBookingList(BookingVO vo);

	// 문의 단건조회
	public BookingVO getBooking(BookingVO vo);

	// 문의 갯수 조회
	public Paging BookingCount(BookingVO vo);

	// 문의 등록
	public int insertBooking(BookingVO vo);

	// 문의 수정
	public int updateBooking(BookingVO vo);

	// 결제 조회
	public PaymentVO getPayment(PaymentVO vo);

	// 결제 등록
	public int insertPayment(PaymentVO vo);

	// 결제 수정
	public int updatePayment(PaymentVO vo);
	
	// 포인트 부여
	public int updatePoint(PaymentVO vo);

	// 포인트 조회
	public Integer getPoint(String MemberId);
}
