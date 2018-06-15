package wj.csv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AutoFillBean {
    public static <T> List<T> fillBean(InputStream file, Class<T> clazz) {
        
    	List<T> beanList = null;
    	T bean = null;
        
        try {
            bean = clazz.newInstance();
            
            //获取指定的类中的所有属性
            Field[] fields = clazz.getDeclaredFields();
            //从文件中获取属性值的map
            Map<Integer, List<String>> fieldMap = readFile(file);
            
            Set<Integer> keySet = fieldMap.keySet(); //得到所有key的集合
            for (Integer lineNumber : keySet) { //遍历map，获取每一行的属性值的list
            	List<String> fieldList = fieldMap.get(lineNumber);
                for(int i = 0; i < fields.length; i++) {
                    String fieldName = fields[i].getName();
                    //获取指定类中的相应属性的set方法，然后把获取到的值封装到对应的属性里面(指定属性名和属性类型)
                    Method method = clazz.getDeclaredMethod("set" 
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1), fields[i].getGenericType().getClass());
                    //从文件中获取对应属性的值，然后调用set方法把值存入类中
                    String value = fieldList.get(i);
                    method.invoke(bean, value);
            	}
              //在bean的所有属性赋值完毕后，将这个bean加到beanList里面去，然后进行下一个bean的属性赋值
                beanList.add(bean);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return beanList;
    }

    public static Map<Integer, List<String>> readFile(InputStream file) {
    	//用bufferedReader来解析文件，将一行一行的内容输入到list里面然后传给AutoFillBean这个方法
    	BufferedReader bReader = null;
		List<String> fieldList = null;
		//用map来装属性值，key是读取的属性值的行数（从0开始）value是装着对应行数属性值的list
		Map<Integer, List<String>> fieldMap = null;
		//读取的属性值的行数，从0开始计数
		int lineNumber = 0;
		
		try {
			bReader = new BufferedReader(new InputStreamReader(file,"utf-8"));
			
			bReader.readLine();
			
			String line = null;
			//用map来装，并且为了保证存储的顺序不乱，选用linkedHashMap
			fieldMap = new LinkedHashMap<Integer, List<String>>();
			fieldList = new ArrayList<String>();
		
			while ((line = bReader.readLine()) != null) {
				if (line.trim() != "") {
					String fields[] = line.trim().split(",");
					for(int i = 0; i < fields.length; i++) {
						fieldList.add(fields[i]);
					}
					fieldMap.put(lineNumber++, fieldList);
					//在map里面加完了就把list清空，保证list每次只存一行的数据
					fieldList.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return fieldMap;
    }
}
