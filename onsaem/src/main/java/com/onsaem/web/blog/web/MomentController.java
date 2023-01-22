package com.onsaem.web.blog.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class MomentController {
	@Autowired
	MomentService momentService;
	@Autowired
	MediaService mediaService;
	
	// 모먼트 상세보기
	@RequestMapping(value="/getMoment", method= RequestMethod.POST)
	@ResponseBody
	public MomentsVO getMoment(String momentId) {
		MomentsVO vo = momentService.getMoment(momentId);
		
		return vo;
	}
	
	
	// 모먼트 등록(글,사진)

	@RequestMapping(value = "/insertMoment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMoment(@RequestParam("uploadfile")MultipartFile[] uploadfile,
										    @RequestParam("momentWrite") String momentWrite,
										    @RequestParam("addUrl") String addUrl,
										    @RequestParam("uploadEnd") String uploadEnd,
										    @RequestParam("publicScope") String publicScope,
										    Authentication authentication) throws IllegalStateException, IOException, ParseException{    
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		MomentsVO momentsVO = new MomentsVO();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(uploadEnd);
		momentsVO.setBlogId(userDetails.getUsername());
		momentsVO.setUploadEnd(date);
		momentsVO.setMomentWrite(momentWrite);
		momentsVO.setAddUrl(addUrl);
		momentsVO.setPublicScope(publicScope);
		
		momentService.momentInsert(momentsVO);
		
		
		List<MediaVO> list= new ArrayList<MediaVO>();
		  MediaVO vo = new MediaVO();
		  vo.setGroupId(momentsVO.getMomentId());
		  vo.setGroups("블로그");
		  vo.setSubGroup("모먼트");
		  //파일 업로드하는 기능 부르기+DB에 저장하기/첨부파일 테이블에 저장할 때 쓰임
		  list = mediaService.uploadMedia(uploadfile,vo);
		  
		  Map<String, Object> momentMap = new HashMap<String, Object>();
		  momentMap.put("moment", momentsVO);
		  momentMap.put("mediaList", list);
		  return momentMap;
	}
	
	// 모먼트 삭제
	@RequestMapping(value="/momentDel", method= RequestMethod.GET)
	@ResponseBody
	public String momentDel(String momentId) {
		momentService.momentDel(momentId);
		
		return "content/blog/myblog";
	}
}
