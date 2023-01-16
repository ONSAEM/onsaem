package com.onsaem.web.common.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

@Controller
public class MediaController {
	@Autowired
	MediaService mediaService;
	

	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public List<MediaVO>  upload(MultipartFile[] uploadfile, Model model, String groupId, String groups) throws IllegalStateException, IOException{    
	  List<MediaVO> list= new ArrayList<MediaVO>();
	  //파일 업로드하는 기능 부르기+DB에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	  list = mediaService.uploadMedia(uploadfile, groupId, groups);
	  return list;
	}

	
	
}
