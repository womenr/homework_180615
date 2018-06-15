package wj.csv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import wj.csv.mapper.UserMapper;

public class CsvFileService {
	
	@Autowired
	private UserMapper userMapper;
	
//	public List<String> readCsv() {
//		//userMapper.readData();
//		List<String> fieldList = new ArrayList<String>();
//		return fieldList;
//	}
//	
//	public String writeCsv(String tableName, String className) {
//		
//		//userMapper.writeData();
//		return null;
//	}
}
