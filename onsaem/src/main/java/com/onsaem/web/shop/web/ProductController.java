package com.onsaem.web.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins="*")
public class ProductController {
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public String empList(Model model) {
		return "content/shop/wFile";
	}
}
