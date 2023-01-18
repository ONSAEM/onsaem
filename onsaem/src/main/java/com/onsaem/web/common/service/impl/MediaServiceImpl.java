package com.onsaem.web.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onsaem.web.common.mapper.MediaMapper;
import com.onsaem.web.common.service.MediaService;
import com.onsaem.web.common.service.MediaVO;

@Service
public class MediaServiceImpl implements MediaService{
	@Autowired
	MediaMapper mediaMapper;
	
	@Value("${part.upload.path}") 
    private String uploadPath;
	
	@Override
	public List<MediaVO> uploadMedia(MultipartFile[] uploadfile,MediaVO media) throws IllegalStateException, IOException {
		 List<MediaVO> list = new ArrayList<MediaVO>();
	      
	      //파일 경로위치에 물리적으로 저장하기
	      for(MultipartFile file : uploadfile) {
	         if(!file.isEmpty()) {
	        	MediaVO vo = new MediaVO();
	        	vo.setUuid(UUID.randomUUID().toString());
	        	vo.setFileName(file.getOriginalFilename());
	        	vo.setMediaType(file.getContentType());
	            
	           
	        	String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            
	            String folderPath = str.replace("/", File.separator);
	            
	            //make folder ==================
	            File uploadPathFolder = new File(uploadPath, folderPath);
	            //File newFile= new File(dir,"파일명");
	            //->부모 디렉토리를 파라미터로 인스턴스 생성
	            
	            if(uploadPathFolder.exists() == false){
	            	uploadPathFolder.mkdirs();
	                //만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미
	                //mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
	    			//mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
	               }

	            String saveName = uploadPath + File.separator + folderPath +File.separator + vo.getUuid() + "_" + vo.getFileName();
	            
	            Path savePath = Paths.get(saveName);
	            
	            file.transferTo(savePath);
	            
	            vo.setFileRoute(folderPath + "/" + vo.getUuid() + "_" + vo.getFileName());
	            vo.setMediaName(vo.getUuid() + "_" + vo.getFileName());
	            list.add(vo);
	         }
	      }
	      
	      //저장한 파일 데이터베이스에 저장하기
	      for(int i=0; i<list.size(); i++) {
	    	  MediaVO vo= new MediaVO();
	          vo.setMediaOrder(i);
	          vo.setFileName(list.get(i).getFileName());
	          vo.setFileRoute(list.get(i).getFileRoute());
	          vo.setMediaName(list.get(i).getUuid()+"_"+list.get(i).getFileName());
	          vo.setGroupId(media.getGroupId());
	          vo.setGroups(media.getGroups());
	          vo.setSubGroup(media.getSubGroup());
	          mediaMapper.insertMedia(vo);
	       }
	      return list;
	}

}
