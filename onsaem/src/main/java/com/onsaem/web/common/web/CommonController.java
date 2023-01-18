/**
 * 
 */
package com.onsaem.web.common.web;

import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public String errorPage() {
		return "content/common/404";
	}
}