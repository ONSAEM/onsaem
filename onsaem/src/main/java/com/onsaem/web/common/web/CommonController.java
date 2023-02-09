/**
 * 
 */
package com.onsaem.web.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onsaem.web.chal.service.ChalService;


@Controller
@CrossOrigin(origins = "*")
public class CommonController {
	@Autowired ChalService chalService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String empList(Model model) {
		//인기있는 챌린지
		model.addAttribute("popularChals", chalService.popularChals());
		
		return "content/main";
	}

	@RequestMapping(value="/testVue", method = RequestMethod.GET)
	public String testVue(Model model) {
		return "content/test/vueTest";
	}
}