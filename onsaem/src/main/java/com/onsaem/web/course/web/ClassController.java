package com.onsaem.web.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.course.service.ClassService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	ClassService classService;
	
	// 강의목록 페이지 이동
	@RequestMapping(value = "/classList", method = RequestMethod.GET)
	public String classList(Model model) {
		model.addAttribute("classList", classService.getClassList(null));
		return "content/course/classList";
	}
}
