package com.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data //getter, setter, toString 등의 어노테이션을 한번에 처리해줌
public class ReplyVO {

	private int replyNo;
	private String reply;
	private String replyer;
	private Date replyDate;
	private int boardNo;
	
	
}
