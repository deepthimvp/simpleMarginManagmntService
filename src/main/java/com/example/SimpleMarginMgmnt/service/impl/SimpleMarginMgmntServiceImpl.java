package com.example.SimpleMarginMgmnt.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SimpleMarginMgmnt.domain.MarginData;
import com.example.SimpleMarginMgmnt.domain.MarginDataRequest;
import com.example.SimpleMarginMgmnt.entity.*;
import com.example.SimpleMarginMgmnt.repository.*;
import com.example.SimpleMarginMgmnt.service.SimpleMarginMgmntService;

@Service
public class SimpleMarginMgmntServiceImpl implements SimpleMarginMgmntService {

	@Autowired
	MarginDataRepository marginDataRepository;
	@Autowired
	MarginDataEntity marginDataEntity;
	@Autowired
	StageMarginDataRepository stgMarginDataRepository;
	@Autowired
	StageMarginDataEntity stgMarginDataEntity;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public List<MarginData> getMarginData() {
		// TODO Auto-generated method stub
		List<MarginDataEntity> dbList = marginDataRepository.findAll();
		List<MarginData> returnList = new ArrayList<MarginData>();
		returnList = dbList.stream().map(dbData-> modelMapper.map(dbData, MarginData.class)).collect(Collectors.toList());
		return returnList;
	}
	@Override
	public Map<String,Object> uploadMarginFile(byte[] bytes) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		XSSFWorkbook workbook = new XSSFWorkbook(bis);
		XSSFSheet sheet = null;
		Map<String,Map<String, Object>> contentMap = new HashMap<>();
		List<Map<String, String>> headerList = new ArrayList<>();
		if (workbook != null) {
			sheet = workbook.getSheetAt(0);
			if (sheet.getLastRowNum() > 0) {
				
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {

					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					Map<String, Object> rowContent = new HashMap<>();
					//int count = 1;
					while (cells.hasNext()) {
						Cell cell = cells.next();
						if (cell.getColumnIndex() > 0) { // skipping comments column from upload
							if (0 == row.getRowNum()) {
								Map<String, String> mapHeader = new HashMap<>();
								mapHeader.put("field", "field" + cell.getColumnIndex());
								mapHeader.put("name", cell.getStringCellValue());
								headerList.add(mapHeader);
							} else {
								//rowContent.put("rowNumber", row.getRowNum());
								if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {
									rowContent.put("field" + cell.getColumnIndex(), String.valueOf(cell.getNumericCellValue()));
								} else {
									
									rowContent.put("field" + cell.getColumnIndex(), cell.getStringCellValue());
								}
								
							}
							//count++;
						}
					}
					if (rowContent.size() > 0) {
						contentMap.put("row"+row.getRowNum(),rowContent);
					}
				}
			}
		}
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("headers", headerList);
		returnMap.put("content", contentMap);
		return this.stageMarginData(returnMap);
		
	}
	@SuppressWarnings("unused")
	private Map<String, Object> stageMarginData(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		Map<String,Map<String, Object>> contentMap = (Map<String, Map<String, Object>>) dataMap.get("content");
		List<Map<String, String>> headerList = (List<Map<String, String>>) dataMap.get("headers");

		List<StageMarginDataEntity> stgDataList = new ArrayList<>();
		
			Iterator<String> keys = contentMap.keySet().iterator();
		while(keys.hasNext()) {
			String row = keys.next();
			StageMarginDataEntity stgEnt = new StageMarginDataEntity();
			stgEnt.setMarginOrder(Integer.valueOf(row.substring(3)));
			for(int i = 0 ; i < headerList.size() ; i++) {
				if(headerList.get(i).get("name").equalsIgnoreCase("instruction")) {
					stgEnt.setInstruction((String)contentMap.get(row).get(headerList.get(i).get("field")));
				} 
				else if(headerList.get(i).get("name").equalsIgnoreCase("base Ccy")) {
					stgEnt.setBaseCcy((String)contentMap.get(row).get(headerList.get(i).get("field")));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("term Ccy")) {
					stgEnt.setTermCcy((String)contentMap.get(row).get(headerList.get(i).get("field")));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("trader Tier")) {
					stgEnt.setTraderTier((String)contentMap.get(row).get(headerList.get(i).get("field")));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("from amount")) {
					int val = (int)Double.parseDouble((String) contentMap.get(row).get(headerList.get(i).get("field")));
					stgEnt.setFromAmount(val);
					}
				else if(headerList.get(i).get("name").equalsIgnoreCase("to amount")) {
					int val = (int)Double.parseDouble((String) contentMap.get(row).get(headerList.get(i).get("field")));
					stgEnt.setToAmount(val);
					}
				else if(headerList.get(i).get("name").equalsIgnoreCase("Amt ccy")) {
					stgEnt.setAmtCcy((String)contentMap.get(row).get(headerList.get(i).get("field")));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("bid operator")) {
					stgEnt.setBidOperator(contentMap.get(row).get(headerList.get(i).get("field")).toString());
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("bid modifier")) {
					stgEnt.setBidModifier(Double.parseDouble((String) contentMap.get(row).get(headerList.get(i).get("field"))));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("ask operator")) {
					stgEnt.setAskOperator(contentMap.get(row).get(headerList.get(i).get("field")).toString());
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("ask modifier")) {
					stgEnt.setAskModifier(Double.parseDouble((String) contentMap.get(row).get(headerList.get(i).get("field"))));
				}
				else if(headerList.get(i).get("name").equalsIgnoreCase("remarks")) {
					stgEnt.setRemarks((String)contentMap.get(row).get(headerList.get(i).get("field")));
				}
			}
			stgDataList.add(stgEnt);
		}
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		if(!stgDataList.isEmpty()){
			stgMarginDataRepository.saveAllAndFlush(stgDataList);
			returnMap.put("MESSAGE", "SUCCESS");
			returnMap.put("StageData", stgDataList);
			
			returnMap.put("fileInstanceId", stgDataList.get(0).getFileInstanceId());
		}
		
		//StageMarginDataEntity stgEnt = stgMarginDataRepository.find
		
		return returnMap;
	}
	@Override
	public Map<String, Object> validateMarginData(int  fileInstanceId) {
		// TODO Auto-generated method stub
		List<String> errorMsgs = new ArrayList<String>();
		Map<String,Object> returnMap = new HashMap<>();

		List<StageMarginDataEntity> stageDataList = stgMarginDataRepository.findByFileInstanceId(fileInstanceId);
		List<MarginData> validStgDataList = new ArrayList<>();
		if(stageDataList != null) {
		stageDataList.forEach( stageEnt -> {
		if(stageEnt.getBaseCcy().length() != 3 && !stageEnt.getBaseCcy().equals("*")){
			errorMsgs.add("Invalid base currency entry");
		}
		if(stageEnt.getTermCcy().length() != 3 && !stageEnt.getTermCcy().equals("*")){
			errorMsgs.add("Invalid Term currency entry");
		}
		if(!stageEnt.getTraderTier().equals("*") && (int)Double.parseDouble(stageEnt.getTraderTier()) < 0){
		errorMsgs.add("Invalid Trader Tier ");
		}
		if(stageEnt.getFromAmount() < 0 || stageEnt.getToAmount() < 0){
		errorMsgs.add("Invalid amount values" );
		}
		if((!(stageEnt.getBidOperator().equals("+"))&& !(stageEnt.getBidOperator().equals("-")) && !(stageEnt.getBidOperator().equals("*")))
				|| (!(stageEnt.getAskOperator().equals("+"))&& !(stageEnt.getAskOperator().equals("-")) && !(stageEnt.getAskOperator().equals("*")))){
		errorMsgs.add("Invalid operators");
		}
		//we can use regular expression to validate 6 decimalplace number
		if(errorMsgs.size() > 0){
			List<String> rowErrorMsgs = new ArrayList<String>() ;
			rowErrorMsgs.addAll(errorMsgs);
		returnMap.put("ERRORS at row " + stageEnt.getMarginOrder(),rowErrorMsgs);
		errorMsgs.clear();
		returnMap.put("MESSAGE", "THERE ARE VALIDATION FAILURES");
		} else {
			MarginData validData = modelMapper.map(stageEnt, MarginData.class);
			validStgDataList.add(validData);
		}
		});
		if(null==returnMap.get("MESSAGE")) {
			returnMap.put("MESSAGE","SUCCESS");
		}
	
		}
		returnMap.put("ValidatedData", validStgDataList);
		return returnMap;

		
	}
	@Override
	public void completeUpload(List<MarginData> stageData) {
		// TODO Auto-generated method stub
		List<MarginDataEntity> saveData = new ArrayList<>();
		saveData = stageData.stream().map((sData) -> modelMapper.map(sData, MarginDataEntity.class))
                .collect(Collectors.toList());
		marginDataRepository.deleteAll();
		marginDataRepository.saveAllAndFlush(saveData);
	}
	@Override
	public Map<String, Object> findMarginData(List<MarginDataRequest> marginDataRequest) {
		// TODO Auto-generated method stub
		 
		List<MarginData> resultList = new ArrayList<MarginData>();
		for(MarginDataRequest request: marginDataRequest) {
			
			List<MarginDataEntity> dbData = marginDataRepository.findByAmountRange(request.getInstruction(),request.getTraderTier(),request.getBaseCcy(),request.getTermCcy(),request.getAmount());
			
			resultList = dbData.stream().map(dData -> modelMapper.map(dData,MarginData.class)).collect(Collectors.toList());
			resultList = resultList.stream().filter(itm-> (itm.getTraderTier().equals("*")) 
						|| (request.getTraderTier().equals(itm.getTraderTier()))).collect(Collectors.toList());
			
		}
		HashMap<String,Object> returnMap = new HashMap<>();
		returnMap.put("result rows count", resultList.size());
		returnMap.put("data", resultList);
		return returnMap;
	}

}
