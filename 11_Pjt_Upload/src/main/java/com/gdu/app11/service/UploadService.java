package com.gdu.app11.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	public int addUpload(MultipartHttpServletRequest multipartRequest);
	public void getUploadList(Model model);
	public void getUploadByNo(int uploadNo, Model model);
	public ResponseEntity<byte[]> display(int attachNo);
	public ResponseEntity<Resource> download(int attachNo, String userAgent);
	public ResponseEntity<Resource> downloadAll(int uploadNo);
	public int removeUpload(int uploadNo);
	public int updateUpload(MultipartHttpServletRequest multipartRequest);
	public int deleteAttach(int attachNo);
}
