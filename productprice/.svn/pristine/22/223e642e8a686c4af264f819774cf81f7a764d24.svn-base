package com.intelligence.autodev.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intelligence.autodev.dao.ITestDao;
import com.intelligence.autodev.persistance.TestMapper;

@Repository
public class TestDaoImpl implements ITestDao {
	
	@Resource
	private TestMapper testMapper;
	
	public void test() {
		
		ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
		for(int i = 0; i < 20000; i++) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("timea", new Date());
			temp.put("timeb", new Date());
			listData.add(temp);
		}
		HashSet<String> setCol = new HashSet<String>();
		setCol.add("timea");
		setCol.add("timeb");
		
		testMapper.insertMultiTest("test2", setCol, listData, null, null, null);
		
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssuccess");
		System.out.println(testMapper.queryTest());
		// System.out.println(testMapper.getTestSql(1));
	}

//    @Controller  
//    @RequestMapping ( "/test/{variable1}" )  
//    public class MyController {  
//      
//        @RequestMapping ( "/showView/{variable2}" )  
//        public ModelAndView showView( @PathVariable String variable1, @PathVariable ( "variable2" ) int variable2) {  
//           ModelAndView modelAndView = new ModelAndView();  
//           modelAndView.setViewName( "viewName" );  
//           modelAndView.addObject( " 需要放到 model 中的属性名称 " , " 对应的属性值，它是一个对象 " );  
//           return modelAndView;  
//        }  
//    }   
	
}
