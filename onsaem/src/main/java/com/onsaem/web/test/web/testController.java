package com.onsaem.web.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@CrossOrigin(origins = "*")
public class testController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String empList() {
		return "content/main";
	}
}
