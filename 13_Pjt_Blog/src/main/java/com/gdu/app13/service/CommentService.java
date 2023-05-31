package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {
	public Map<String, Object> addComment(HttpServletRequest request);
	public Map<String, Object> addCommentCount(int blogNo);
	public Map<String, Object> getCommentList(HttpServletRequest request);
}
