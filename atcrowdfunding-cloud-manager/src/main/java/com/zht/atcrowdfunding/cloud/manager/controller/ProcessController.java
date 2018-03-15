package com.zht.atcrowdfunding.cloud.manager.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zht.atcrowdfunding.cloud.manager.service.ProcessService;
import com.zht.atcrowdfunding.common.BaseController;
import com.zht.atcrowdfunding.common.bean.Page;

@Controller
@RequestMapping("/process")
public class ProcessController extends BaseController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ProcessService processService;
	
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public Object delete(@PathVariable("id") String id) {
		start();
		
		try {
			processService.delete(id);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}
	
	@RequestMapping("/loadImg/{id}")
	public void loadImg(@PathVariable("id") String id, HttpServletResponse resp) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		
		String url = "http://eureka-activiti-service/loadImgById/" + id;
		ResponseEntity<byte[]> response = restTemplate.exchange(
	        url,
	        HttpMethod.POST,
	        new HttpEntity<byte[]>(headers),
	        byte[].class); 
	    byte[] result = response.getBody();

	    InputStream in = new ByteArrayInputStream(result);
		OutputStream out = resp.getOutputStream();
		
		int i = -1;
		while ( (i = in.read()) != -1 ) {
			out.write(i);
		}

	}
	
	@RequestMapping("/showImg/{id}")
	public String showImg(@PathVariable("id") String id, Model model) {
		model.addAttribute("pdid", id);
		return "process/show";
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload(HttpServletRequest req) {
		start();
		
		try {
			MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
			
			MultipartFile file = request.getFile("procDefFile");
			
//			String name = file.getName();
//			String filename = file.getOriginalFilename();
//			
//			System.out.println("name = " + name);
//			System.out.println("filename = " + filename);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			String uuid = UUID.randomUUID().toString();
			String fileName = file.getOriginalFilename();
			final File tempFile = File.createTempFile(uuid, fileName.substring(fileName.lastIndexOf(".")));
			//文件复制，文件拷贝
			file.transferTo(tempFile);
		    FileSystemResource resource = new FileSystemResource(tempFile);  
		    MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();  
		    param.add("pdfile", resource);
			restTemplate.postForObject("http://eureka-activiti-service/depolyProcDef",param,String.class);

			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize) {
		start();
		
		try {
			
			int count = processService.pageQueryProcDefCount();
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			List<Map<String, Object>> pdMaps = processService.pageQueryProcDefData(paramMap);
			
			Page<Map<String, Object>> pdPage = new Page<>();
			pdPage.setTotalPagesize(count);
			pdPage.setPageno(pageno);
			pdPage.setPagesize(pagesize);
			pdPage.setDatas(pdMaps);
			data(pdPage);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		return end();
	}
}
