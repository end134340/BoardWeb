package com.yedam.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private String userId;
	private String password;
	private String userName;
	private String responsibility;
	private String images;
}
