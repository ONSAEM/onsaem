package com.onsaem.web.course.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
import com.onsaem.web.common.service.Paging;
import com.onsaem.web.common.service.PaymentVO;
import com.onsaem.web.course.mapper.BookingMapper;
import com.onsaem.web.course.service.BookingService;
import com.onsaem.web.course.service.BookingVO;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassService;
import com.onsaem.web.course.service.ClassVO;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingMapper bookingMapper;
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	ClassService classService;
	
	@Override
	public Map<String, Object> getBookingList(BookingVO vo, Paging paging) {
		Paging newPaging = bookingMapper.BookingCount(vo);
		newPaging.setPage(paging.getPage());
		newPaging.setTotalRecord(newPaging.getTotalRecord());
		vo.setFirst(newPaging.getFirst());
		vo.setLast(newPaging.getLast());
		List<BookingVO> reviewList = bookingMapper.getBookingList(vo);
		for(BookingVO bvo : reviewList) {
			ClassVO cvo = new  ClassVO();
			cvo.setCNo(bvo.getCNo());
			bvo.setBClass(classService.getClass(cvo));
			ClassInfoVO clvo = new ClassInfoVO();
			clvo.setClassId(bvo.getBClass().getClassId());
			bvo.getBClass().setClassInfo(classService.getClassInfo(clvo));
			MediaVO mvo = new MediaVO();
			mvo.setGroupId(bvo.getBClass().getClassId());
			bvo.getBClass().getClassInfo().setMedia(mediaService.getMedia(mvo));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", reviewList);
		result.put("bPaging", newPaging);
		return result;
	}

	@Override
	public BookingVO getBooking(BookingVO vo) {
		BookingVO bvo = bookingMapper.getBooking(vo);
		ClassVO cvo = new  ClassVO();
		cvo.setCNo(bvo.getCNo());
		bvo.setBClass(classService.getClass(cvo));
		ClassInfoVO clvo = new ClassInfoVO();
		clvo.setClassId(bvo.getBClass().getClassId());
		bvo.getBClass().setClassInfo(classService.getClassInfo(clvo));
		MediaVO mvo = new MediaVO();
		mvo.setGroupId(bvo.getBClass().getClassId());
		bvo.getBClass().getClassInfo().setMedia(mediaService.getMedia(mvo));
		return bvo;
	}

	@Override
	public int BookingCount(BookingVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BookingVO insertBooking(BookingVO vo) {
		if(bookingMapper.insertBooking(vo)>0) {
			return vo;
		}else {
			return null;
		}
	}

	@Override
	public boolean updateBooking(BookingVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PaymentVO getPayment(PaymentVO vo) {

		return bookingMapper.getPayment(vo);
	}
	
	@Override
	public int insertPayment(PaymentVO vo) {

		return bookingMapper.insertPayment(vo);
	}

	@Override
	public int updatePayment(PaymentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePoint(PaymentVO vo) {
		
		return bookingMapper.updatePoint(vo);
	}

	@Override
	public Integer getPoint(String MemberId) {

		return bookingMapper.getPoint(MemberId);
	}

}
