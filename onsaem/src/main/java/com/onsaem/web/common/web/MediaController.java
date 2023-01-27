package com.onsaem.web.common.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.mapper.MediaMapper;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

@Controller
@CrossOrigin(origins = "*")
public class MediaController {
	
	@Autowired
	MediaService mediaService;
	
	@Autowired
	MediaMapper mediaMapper;
	
	@Value("${part.upload.path}")
	private String uploadPath;
	
	@RequestMapping(value = "/mediaTest", method = RequestMethod.POST)
	@ResponseBody
	public List<MediaVO>  mediaTest(MultipartFile[] uploadfile, String groupId, String groups, String subGroup) throws IllegalStateException, IOException{    
	  List<MediaVO> list= new ArrayList<MediaVO>();
	  System.out.println(uploadfile);
	  MediaVO vo = new MediaVO();
	  vo.setGroupId(groupId);
	  vo.setGroups(groups);
	  vo.setSubGroup(subGroup);
	  //파일 업로드하는 기능 부르기+DB에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	  list = mediaService.uploadMedia(uploadfile,vo);
	  return list;
	}
	
	// 미디어 테스트 작동 안함(연결매퍼 다른곳에 쓰여짐)(삭제예정)
	@RequestMapping(value = "/mediaTest", method = RequestMethod.GET)
	public String mediaTest(){
	  return "content/test/mediaTest";
	}
	
	
	
}
