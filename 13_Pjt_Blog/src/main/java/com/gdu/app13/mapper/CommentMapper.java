package com.gdu.app13.mapper;

import java.util.List;
import java.util.Map;

import com.gdu.app13.domain.CommentDTO;

public interface CommentMapper {
	public int addComment(CommentDTO commentDTO);
	public int getCommentCount(int blogNo);
	public List<CommentDTO> getCommentList(Map<String, Object> map);
	
}
