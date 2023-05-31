package com.gdu.app11.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app11.service.UploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/upload")
@Controller
public class UploadController {

	// field
	private final UploadService uploadService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		uploadService.getUploadList(model);
		return "upload/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/add.do")
	// MultipartHttpServletRequest를 사용하려면 pom.xml에 업로드 시 필수로 주석달아놓은 메이븐이 필수
	public String add(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) { // MultipartHttpServletRequest : 첨부된 파일을 전달할 때 사용하는 request
		int uploadResult = uploadService.addUpload(multipartRequest);
		
		redirectAttributes.addFlashAttribute("uploadResult", uploadResult);
		return "redirect:/upload/list.do"; 
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo
					, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	@GetMapping("/display.do")
	public ResponseEntity<byte[]> display(@RequestParam("attachNo") int attachNo){
		return uploadService.display(attachNo);
	}
	
	@GetMapping("/download.do")
	public ResponseEntity<Resource> download(@RequestParam("attachNo") int attachNo, @RequestHeader("User-Agent") String userAgent) {
		return uploadService.download(attachNo, userAgent);
	}
	
	@GetMapping("/downloadAll.do")
	public ResponseEntity<Resource> downloadAll(@RequestParam("uploadNo") int uploadNo) {
		return uploadService.downloadAll(uploadNo);
	}
	
	@PostMapping("/removeUpload.do")
	public String removeUpload(@RequestParam("uploadNo") int uploadNo, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", uploadService.removeUpload(uploadNo));
		return "redirect:/upload/list.do";
	}
	
	@GetMapping("/editPage.do")
	public String editPage(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	@PostMapping("/updateUpload.do")
	public String updateUpload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int uploadNo = Integer.parseInt(multipartRequest.getParameter("uploadNo"));
		redirectAttributes.addFlashAttribute("updateResult", uploadService.updateUpload(multipartRequest));
		return "redirect:/upload/detail.do?uploadNo=" + uploadNo;
	}
	
	@GetMapping("/deleteAttach.do")
	public String deleteAttach(@RequestParam("attachNo") int attachNo, @RequestParam("uploadNo") int uploadNo) {
		uploadService.deleteAttach(attachNo);
		return "redirect:/upload/editPage.do?uploadNo=" + uploadNo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
