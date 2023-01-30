/**
 * 
 */
package com.onsaem.web.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = "*")
public class CommonController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String empList() {
		return "content/main";
	}

	@RequestMapping(value="/errorPage", method = RequestMethod.GET)
	public String errorPage() {
		return "content/error/404";
	}
	
	@RequestMapping(value="/testblog", method = RequestMethod.GET)
	public String testblog() {
		return "content/test/blogtest";
	}
	@RequestMapping(value="/testVue", method = RequestMethod.GET)
	public String testVue() {
		return "content/test/vueTest";
	}
}