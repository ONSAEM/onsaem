package com.onsaem.web.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.course.service.ClassInfoVO;
import com.onsaem.web.course.service.ClassService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	ClassService classService;
	
	// 강의목록 페이지 이동
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public String classList(ClassInfoVO vo, Model model) {
		model.addAttribute("classList", classService.getClassList(vo));
		return "content/course/classList";
	}
	
	// 강의상세 페이지 이동
	@RequestMapping(value = "/classDetail", method = RequestMethod.GET)
	public String classDetail(ClassInfoVO vo, Model model) {
		model.addAttribute("class", classService.getClass(vo));
//		model.addAttribute("mediaList");
//		model.addAttribute("reviewList");
		return "content/course/classDetail";
	}
}
