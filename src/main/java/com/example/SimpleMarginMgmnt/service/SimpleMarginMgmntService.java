package com.example.SimpleMarginMgmnt.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.SimpleMarginMgmnt.domain.MarginData;
import com.example.SimpleMarginMgmnt.domain.MarginDataRequest;

@Component
@Service
public interface SimpleMarginMgmntService {
	
	public List<MarginData> getMarginData();

	public Map<String, Object> uploadMarginFile(byte[] bs) throws IOException;

	public Map<String, Object> validateMarginData(int fileInstanceId);
	
	public void completeUpload(List<MarginData> marginDataList);

	public Map<String, Object> findMarginData(List<MarginDataRequest> marginDataRequest);

}
