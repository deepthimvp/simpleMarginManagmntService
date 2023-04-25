package com.example.SimpleMarginMgmnt.controller;

import java.util.*;
import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SimpleMarginMgmnt.service.SimpleMarginMgmntService;
import com.example.SimpleMarginMgmnt.domain.*;

@Component
@RestController
public class MarginMangmntController {
	
	@Autowired
	SimpleMarginMgmntService simpleMarginMgmntService;
	
	@PostMapping("/sayHi")
	public String sayHi(@RequestBody String name) {
		return "Hi "+name+"! How are you doing";
	}
	
	@GetMapping("/marginData")
	public List<MarginData> getMarginData() {
		return simpleMarginMgmntService.getMarginData();
	}
	
	@PostMapping("/findMarginData")
	public Map<String,Object> findMarginData(@RequestBody List<MarginDataRequest> marginDataRequests){
		return simpleMarginMgmntService.findMarginData(marginDataRequests);
	}
	
	@PostMapping(value = "/uploadMarginFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Map<String,Object> uploadMarginFile(@RequestBody MultipartFile marginFile) throws Exception {
		Map<String, Object> returnMap = null;
		try {
			returnMap = new HashMap<String,Object>();
			if(marginFile.getOriginalFilename().endsWith("xlsx")){
				returnMap = simpleMarginMgmntService.uploadMarginFile(marginFile.getBytes());
			}
			/*
			 * if(((String) returnMap.get("message")).equalsIgnoreCase("SUCCESS")) {
			 * simpleMarginMgmntService.validateMarginData(returnMap.get("fileInstanceId"));
			 * }
			 */
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMap.put("message", "FAILED - EXCEPTION");
			e.printStackTrace();
		}
		
		return returnMap;
		
	}
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/validateFileData/{fileInstanceId}")
	public Map<String,Object> validateFileData(@PathVariable(value = "fileInstanceId") int fileInstanceId) throws Exception {
		Map<String, Object> returnMap = null;
		try {
			returnMap = new HashMap<String,Object>();
			  returnMap = simpleMarginMgmntService.validateMarginData(fileInstanceId);
			simpleMarginMgmntService.completeUpload((List<MarginData>) returnMap.get("ValidatedData"));
			//returnMap.put("message", "SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			returnMap.put("message", "FAILED - VALIDATE EXCEPTION");
			e.printStackTrace();
		}
		return returnMap;
	}
	
}

