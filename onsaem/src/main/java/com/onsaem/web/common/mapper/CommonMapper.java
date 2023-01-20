package com.onsaem.web.common.mapper;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
	
	// 은행목록 가져오기
	public List<Map<String, String>> getBankList();
}
