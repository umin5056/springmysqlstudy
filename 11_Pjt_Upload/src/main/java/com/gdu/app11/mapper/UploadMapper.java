package com.gdu.app11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;

@Mapper
public interface UploadMapper {
	
	// getUploadList
	public List<UploadDTO> getUploadList();
	
	// addUpload
	public int addAttach(AttachDTO attachDTO);
	public int addUpload(UploadDTO uploadDTO);
	
	// getUploadByNo
	public UploadDTO getUploadByNo(int uploadNo);
	
	// getUploadByNo, downloadAll
	public List<AttachDTO> getAttachList(int uploadNo);
	
	// display, download
	public AttachDTO getAttachByNo(int attachNo);
	
	// download
	public int increaseDownloadCount(int attahcNo);
	
	// removeUpload
	public int removeUpload(int uploadNo);
	
	// updateUpload
	public int updateUpload(UploadDTO uploadDTO);
	
	// deleteAttach
	public int deleteAttach(int attachNo);
	
	// removeWrongfileScheduler
	public List<AttachDTO> getAttachListInYesterday();
	
}
