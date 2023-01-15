package com.onsaem.web.blog.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.blog.service.BlogReplyService;
import com.onsaem.web.blog.service.BlogWriteService;
import com.onsaem.web.blog.service.BlogWriteVO;
import com.onsaem.web.blog.service.CategoriesVO;
import com.onsaem.web.blog.service.MomentService;
import com.onsaem.web.blog.service.MomentsVO;
import com.onsaem.web.common.service.LikeVO;
import com.onsaem.web.common.service.RepliesVO;

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
	
	// 블로그 메인으로 이동 (조회)
	@RequestMapping(value = "/blogMain", method = RequestMethod.GET)
	public String blogWriteList(Model model) {
		model.addAttribute("blogList", blogWriteService.getBlogList(null));
		return "content/blog/blogMain";
	}
	
	// 내 블로그로 이동
	@RequestMapping(value = "/myblog", method = RequestMethod.GET)
	public String myblog(Model model, String userId, CategoriesVO vo, MomentsVO mVo) {
		model.addAttribute("myblog", blogWriteService.myBlog(userId));	
		vo.setBlogId(userId);
		model.addAttribute("category", blogWriteService.cateList(vo));
		model.addAttribute("recentWrite", blogWriteService.recentWrite(userId)); // 최신글 조회
		mVo.setBlogId(userId);
		model.addAttribute("moments", momentService.getMomentList(mVo));
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
		//MemberVO vo2 = (MemberVO)session.getAttribute("member"); // 아이디로 닉네임 찾아오게 글 등록 시, 매퍼에서 멤버 테이블과 조인하기(닉네임 필요)
		//vo.setWriterNickname(vo2.getNickname());
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
