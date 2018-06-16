package wj.csv.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import wj.base.controller.BaseController;
import wj.csv.pojo.User;
import wj.csv.service.CsvFileService;


@Controller
public class CsvFileController extends BaseController{
	
	@Autowired
	private CsvFileService service;
	
	@RequestMapping("/home")
	public String home() {
		return "files/upload";
	}
	
/*    @RequestMapping(value="upload", method=RequestMethod.GET)  
    public String showUploadPage(){   
        return "uploadFile";         //view文件夹下的上传单个文件的页面  
    }  */
	
	@ResponseBody
	@RequestMapping(value="/upload",method=RequestMethod.POST) 
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{

		if(file.isEmpty()) {
        	model.addAttribute("fileError", "上传文件内容为空");
        	return "files/upload"; 
        }
        //判断文件是否是csv文件
		String fileName = file.getOriginalFilename();
		if (!(fileName.endsWith(".csv"))) {
			model.addAttribute("fileError", "所上传的文件必须是csv文件");
        	return "files/upload"; 
		}
		//将上传上来的文件直接转化成流
		
		InputStream fileInput = file.getInputStream();
		
		
		
		
		
		
		return "success";
		
		
	}
}
