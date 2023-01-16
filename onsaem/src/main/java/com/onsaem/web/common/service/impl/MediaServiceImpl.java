package com.onsaem.web.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.mapper.MediaMapper;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

@Service
public class MediaServiceImpl implements MediaService{
	@Autowired
	MediaMapper mediaMapper;
	
	@Override
	public List<MediaVO> uploadMedia(MultipartFile[] uploadfile, String groupId, String groups) throws IllegalStateException, IOException {
		 List<MediaVO> list = new ArrayList<MediaVO>();
	      
	      //파일 경로위치에 물리적으로 저장하기
	      for(MultipartFile file : uploadfile) {
	         if(!file.isEmpty()) {
	        	MediaVO vo = new MediaVO(UUID.randomUUID().toString(),
	        			file.getOriginalFilename(),
	        			file.getContentType());
	            list.add(vo);
	                        
	            File newFileName = new File(vo.getUuid()+"_"+vo.getFileName());
	            
	            file.transferTo(newFileName);
	         }
	      }
	      
	      //저장한 파일 데이터베이스에 저장하기
	      for(int i=0; i<list.size(); i++) {
	    	  MediaVO vo= new MediaVO();
	          vo.setMediaOrder(i);
	          vo.setFileName(list.get(i).getFileName());
	          vo.setMediaName(list.get(i).getUuid()+"_"+list.get(i).getFileName());
	          vo.setGroupId(groupId); //test용, 게시글 번호
	          vo.setGroups(groups);//test용, board에 넣음\
	          mediaMapper.insertMedia(vo);
	       }
	      return list;
	}

}
