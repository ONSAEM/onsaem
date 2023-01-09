package com.onsaem.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.onsaem.web.course.service.ClassService;

@SpringBootTest
class ClassControllertest {
	
	@Autowired
	ClassService classService;
	
	@Test
	public String classList(Model model) {
		model.addAttribute("classList", classService.getClassList(null));
		return "content/course/classList";
	}
}
