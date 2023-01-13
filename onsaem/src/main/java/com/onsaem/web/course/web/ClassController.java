package com.onsaem.web.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onsaem.web.common.service.Paging;
import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassService;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	ClassService classService;
	
	// 강의목록 페이지 이동 (강의목록, 인기강의목록)
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public String classList(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		model.addAttribute("classList", classService.getClassList(vo,paging));
		model.addAttribute("maxPrice", classService.classMaxPrice(vo));
		model.addAttribute("minPrice", classService.classMinPrice(vo));
		model.addAttribute("page", classService.classCount(vo));
		return "content/course/classList";
	}

	// 강의검색, 페이지, 정렬 처리
	@RequestMapping(value = "/classSearch", method = RequestMethod.GET)
	@ResponseBody
	public List<ClassInfoVO> classSearch(ClassInfoVO vo, Model model, Paging paging) {
		paging.setPageUnit(9);
		return classService.getClassList(vo,paging);
	}
	
	// 새 페이징 가져오기
	@RequestMapping(value = "/getPaging", method = RequestMethod.GET)
	@ResponseBody
	public Paging getPaging(ClassInfoVO vo, Paging paging) {
		Paging newPaging = classService.classCount(vo);
		newPaging.setPageUnit(9);
		newPaging.setPage(paging.getPage());
		return newPaging;
	}
	
	// 강의상세 페이지 이동 (강의정보, 미디어목록, 후기목록, 문의목록)
	@RequestMapping(value = "/classDetail", method = RequestMethod.GET)
	public String classDetail(ClassInfoVO vo, Model model) {
		model.addAttribute("class", classService.getClass(vo));
		model.addAttribute("mediaList", classService.classMediaList(vo));
//		model.addAttribute("reviewList");
		return "content/course/classDetail";
	}
}
