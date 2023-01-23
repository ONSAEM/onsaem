package com.onsaem.web.blog.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.blog.service.BlogReplyService;
import com.onsaem.web.blog.service.BlogService;
import com.onsaem.web.blog.service.BlogVO;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;
import com.onsaem.web.blog.service.CategoriesVO;
import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.RepliesVO;
import com.onsaem.web.common.service.ReportVO;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogWriteController {
	@Autowired
	BlogWriteService blogWriteService;
	@Autowired
	BlogReplyService replyService;
	@Autowired
	MomentService momentService;
	@Autowired
	BlogService blogService;
	
	@Value("${part.upload.path}") 
    private String uploadPath;
	
	// 블로그 메인으로 이동 (조회)
	@RequestMapping(value = "/blogMain", method = RequestMethod.GET)
	public String blogWriteList(Model model) {
		model.addAttribute("blogList", blogWriteService.getBlogList(null));
		return "content/blog/blogMain";
	}
	
	// 내 블로그로 이동
	@RequestMapping(value = "/myblog", method = RequestMethod.GET)
	public String myblog(Model model, String userId, CategoriesVO vo, MomentsVO mVo, BlogVO bVo, LikeVO lVo,Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		
		model.addAttribute("myblog", blogWriteService.myBlog(userId));	
		vo.setBlogId(userId);
		model.addAttribute("category", blogWriteService.cateList(vo));
		model.addAttribute("recentWrite", blogWriteService.recentWrite(userId)); // 최신글 조회
		mVo.setBlogId(userId);
		model.addAttribute("moments", momentService.getMomentList(mVo));
		// getBlog를 써서 유저아이디 받아오고, getbloginfo라고 이름 지어주기
		model.addAttribute("blogInfo", blogService.getBlogInfo(userId));
		System.out.println("방분한 블로그 정보:"+model.getAttribute("blogInfo"));
		
		lVo.setGroupId(userId); // 구독 당한 사람
		lVo.setMemberId(id); // 구독 한 사람
		model.addAttribute("subCount", blogService.subCount(lVo));
		System.out.println("구독 여부:"+model.getAttribute("subCount"));
		
		model.addAttribute("subMeList", blogService.subMeList(lVo)); // 나를 구독한
		model.addAttribute("mySubList", blogService.mySubList(lVo)); // 내가 구독한
		
		model.addAttribute("mmtCnt",momentService.momentCnt(id));
		System.out.println("현재모먼트 수:"+model.getAttribute("mmtCnt"));
		
		return "content/blog/myblog";
	}
	
	// 블로그 글 상세 페이지로 이동(단건조회)
	@RequestMapping(value = "/myblog/blogWrite", method = RequestMethod.GET)
	public String blogWrite(Model model, String bno,BlogWriteVO bVo, LikeVO vo, RepliesVO rVo, CategoriesVO cVo,Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		
		model.addAttribute("blogWrite", blogWriteService.getBlog(bno)); // 블로그 단건 조회
		
		vo.setMemberId(id);
		vo.setGroupId(bno);
		model.addAttribute("likeCount", blogWriteService.likeCount(vo)); // 좋아요 조회
		model.addAttribute("cntBlogLike", blogWriteService.cntBlogLike(vo)); // 좋아요 수
		
		rVo.setGroupId(bno);
		model.addAttribute("replyCnt", replyService.replyCnt(rVo)); // 댓글 수
		model.addAttribute("replyList", replyService.replyList(bno)); // 댓글 조회

		cVo.setBlogId(id);
		model.addAttribute("category", blogWriteService.cateList(cVo) ); // 카테고리 조회
		
		return "content/blog/blogWrite";
	}
	
	// 블로그 글 등록 페이지로 이동
	@RequestMapping(value = "/myblog/blogWrite/blogInsert", method = RequestMethod.GET)
	public String blogInsertPage(Model model) {
		//model.addAttribute("blogInsert", blogWriteService.blogInsert(null));
		return "content/blog/blogInsert";
	}
	
	// 블로그 글 등록 처리(등록) 세션 아이디 값 vo에 다시 담는 방법 몰름... 
	@RequestMapping(value = "/myblog/blogWrite/blogInsert", method = RequestMethod.POST)
	@ResponseBody  //ajax 응답은 responseBody
	public BlogWriteVO blogInsert(Model model, BlogWriteVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setBlogId(id);
		
		model.addAttribute("blogInsert", blogWriteService.blogInsert(vo));
		return vo;
	}
	
	// 좋아요 추가
	@RequestMapping(value = "/addBlogLike", method = RequestMethod.POST)
	@ResponseBody
	public LikeVO addBlogLike(LikeVO vo, Authentication authentication) {		
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setMemberId(id);
		blogWriteService.addBlogLike(vo);
		return blogWriteService.cntBlogLike(vo);
	}
	// 좋아요 삭제
	@RequestMapping(value = "/delBlogLike", method = RequestMethod.POST)
	@ResponseBody
	public LikeVO delBlogLike(LikeVO vo, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();		
		vo.setMemberId(id);
		blogWriteService.delBlogLike(vo);
		
		return blogWriteService.cntBlogLike(vo);
	}
	
	// 댓글 신고
	@RequestMapping(value = "/addBan", method = RequestMethod.POST)
	@ResponseBody
	public ReportVO addBan(ReportVO vo,Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		String id = userDetails.getUsername();
		vo.setFromId(id);
		blogWriteService.addBan(vo);
		return vo;
	}
	
	// 네이버 에디터 멀티 이미지 등록
	@RequestMapping(value="/multiImageUploader", method=RequestMethod.POST)
	public void multiImageUploader(HttpServletRequest request, HttpServletResponse response){
		try {
			//파일정보
			String sFileInfo = "";
			//파일명을 받는다 - 일반 원본파일명
			String sFilename = request.getHeader("file-name");
			//파일 확장자
			String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
			//확장자를소문자로 변경
			sFilenameExt = sFilenameExt.toLowerCase();
				
			//이미지 검증 배열변수
			String[] allowFileArr = {"jpg","png","bmp","gif","mp4"};

			//확장자 체크
			int nCnt = 0;
			for(int i=0; i<allowFileArr.length; i++) {
				if(sFilenameExt.equals(allowFileArr[i])){
					nCnt++;
				}
			}

			//이미지가 아니라면
			if(nCnt == 0) {
				PrintWriter print = response.getWriter();
				print.print("NOTALLOW_"+sFilename);
				print.flush();
				print.close();
			} else {
				//디렉토리 설정 및 업로드	
				
				//파일경로
				File file = new File(uploadPath);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
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
				
				String sRealFileNm = "";
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//				String today= formatter.format(new java.util.Date());
				sRealFileNm = UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
				String rlFileNm = uploadPath + '/'+folderPath+'/' +sRealFileNm;
				
				///////////////// 서버에 파일쓰기 ///////////////// 
				InputStream inputStream = request.getInputStream();
				OutputStream outputStream=new FileOutputStream(rlFileNm);
				int numRead;
				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
					outputStream.write(bytes,0,numRead);
				}
				if(inputStream != null) {
					inputStream.close();
				}
				outputStream.flush();
				outputStream.close();
				
				///////////////// 이미지 /////////////////
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName="+ sFilename;
				sFileInfo += "&sFileURL="+"/fileView/"+folderPath+'/'+sRealFileNm;
				System.out.println(sRealFileNm);
				PrintWriter printWriter = response.getWriter();
				printWriter.print(sFileInfo);
				printWriter.flush();
				printWriter.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 썸네일
	@RequestMapping(value = "/myblog/thumbnail", method = RequestMethod.POST)
	@ResponseBody
	public String getSrc(String source) {
        // 이미지 태그를 추출하기 위한 정규식.
        Pattern pattern  =  Pattern.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
        // 내용 중에서 이미지 태그를 찾아라!
        Matcher match = pattern.matcher(source);
        String imgTag = null;
        if(match.find()){ // 이미지 태그를 찾았다면,,
            imgTag = match.group(1); // 글 내용 중에 첫번째 이미지 태그를 뽑아옴.
        }
        return imgTag;
    }
	
	
	// 내 블로그 제목 검색
//	@RequestMapping(value = "/myblog/searchWrite", method = RequestMethod.POST)
//	public String searchWrite(Model model, @RequestParam(value = "data", required = false) String data,
//			@RequestParam(value = "blogId", required = false) String blogId){
//		System.out.println("============================"+blogId);
//		System.out.println("-=--=============="+data);
//		model.addAttribute("myblog", blogWriteService.searchWrite(blogId,data));
//		return "content/blog/blogWrite";
//	}
	
	
	
	// 블로그 글 수정 페이지로 이동
//	@RequestMapping(value = "/myblog/blogWrite/blogUpdate/{userId}/{bno}", method = RequestMethod.GET)
//	public String blogUpdatePage(Model model, @PathVariable String userId, @PathVariable String bno) {
//		model.addAttribute("blogUpdate", blogWriteService.getBlog(userId, bno));
//		return "content/blog/blogUpdate";
//	}
//	// 블로그 글 수정 처리(수정)
//	@RequestMapping(value = "/myblog/blogWrite/blogUpdate", method = RequestMethod.POST)
//	@ResponseBody  //ajax 응답은 responseBody
//	public BlogWriteVO blogUpdate(Model model, BlogWriteVO vo) {
//		model.addAttribute("blogUpdate", blogWriteService.blogUpdate(vo));
//		return vo;
//	}
//	// 블로그 글 삭제 처리(삭제)
//	@RequestMapping(value = "/myblog/blogWrite/blogDelete/{userId}/{bno}", method = RequestMethod.GET)
//	public String blogDelete(Model model, BlogWriteVO vo) {
//		model.addAttribute("blogDelete", blogWriteService.blogDelete(vo));
//		return "content/blog/myblog";
//	}
}
