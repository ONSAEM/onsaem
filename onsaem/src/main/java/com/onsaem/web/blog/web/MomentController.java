package com.onsaem.web.blog.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;
/**
 * 
 * @author 정호경
 * 사용자의 모먼트 관련
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class MomentController {
	@Autowired
	MomentService momentService;
	@Autowired
	MediaService mediaService;
	@Autowired
	BlogWriteService blogWriteService;
	
	// 모먼트 상세보기
	@RequestMapping(value="/getMoment", method= RequestMethod.POST)
	@ResponseBody
	public MomentsVO getMoment(Model model, String momentId, LikeVO lvo ) {
		MomentsVO vo = momentService.getMoment(momentId);
		return vo;
	}
	
	// 모먼트 상세보기(메인)
		@RequestMapping(value="/getMySubMoment", method= RequestMethod.POST)
		@ResponseBody
		public MomentsVO getMySubMoment(Model model, String momentId) {
			MomentsVO vo = momentService.getMySubMoment(momentId);
			return vo;
		}
	
	
	// 모먼트 등록(글,사진)

	@RequestMapping(value = "/insertMoment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMoment(@RequestParam("uploadfile")MultipartFile[] uploadfile,
										    MomentsVO momentsVO,
										    Authentication authentication) throws IllegalStateException, IOException, ParseException{    
		// 등록
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		momentsVO.setBlogId(userDetails.getUsername());

		
		momentService.momentInsert(momentsVO);
		
		// 사진 등록
		List<MediaVO> list= null;
		  MediaVO vo = new MediaVO();
		  vo.setGroupId(momentsVO.getMomentId());
		  vo.setGroups("블로그");
		  vo.setSubGroup("모먼트");
		  //파일 업로드하는 기능 부르기+DB에 저장하기/첨부파일 테이블에 저장할 때 쓰임
		  list = mediaService.uploadMedia(uploadfile,vo);
		  
		  // 결과 전송
		  Map<String, Object> momentMap = new HashMap<String, Object>();
		  momentMap.put("moment", momentsVO);
		  momentMap.put("mediaList", list);
		  return momentMap;
	}
	
	// 모먼트 삭제
	@RequestMapping(value="/momentDel", method= RequestMethod.POST)
	@ResponseBody
	public String momentDel(String momentId) {
		momentService.momentDel(momentId);
		
		return "success";
	}
}
