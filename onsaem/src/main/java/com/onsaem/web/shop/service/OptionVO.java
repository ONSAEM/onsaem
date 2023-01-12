package com.onsaem.web.shop.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OptionVO {
	String optionId;
	String productId;
	String optionContent;
	String topOptionId;
}
