package wj.csv.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wj.csv.mapper.AccountMapper;
import wj.csv.mapper.ItemsMapper;
import wj.csv.mapper.UserMapper;
import wj.csv.pojo.Account;
import wj.csv.pojo.Items;
import wj.csv.pojo.User;
import wj.csv.utils.AutoFillBean;

@Service
public class CsvFileService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	public Class checkFileName(String fileName) {
		if (fileName.startsWith("User")) {
			Class<User> clazz = User.class;
			return clazz;
		} else if (fileName.startsWith("Items")) {
			Class<Items> clazz = Items.class;
			return clazz;
		} else if (fileName.startsWith("Account")) {
			Class<Account> clazz = Account.class;
			return clazz;
		} else {
			return null;
		}
	}
	
	public <T> void insertData(InputStream file, Class<T> clazz) {
		List<T> beanList = AutoFillBean.fillBean(file, clazz);
		//判断获得的beanList是属于哪个类的，然后用相应的mapper进行数据插入操作
		if (clazz.getName().endsWith("User")) {
			List<User> userList = (List<User>) beanList;
			for (User user : userList) {
				userMapper.insert(user);
			}
		}
		
		if (clazz.getName().endsWith("Account")) {
			List<Account> accountList = (List<Account>) beanList;
			for (Account account : accountList) {
				accountMapper.insert(account);
			}
		}
		
		if (clazz.getName().endsWith("Items")) {
			List<Items> itemsList = (List<Items>) beanList;
			for (Items items : itemsList) {
				itemsMapper.insert(items);
			}
		}
	}

//	public String writeCsv(String tableName, String className) {
//		
//		//userMapper.writeData();
//		return null;
//	}
}
