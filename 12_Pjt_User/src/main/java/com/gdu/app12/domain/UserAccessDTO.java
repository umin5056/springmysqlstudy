package com.gdu.app12.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccessDTO {
	private int userNo;
	private String id;
	private Date lastLoginAt;
}
