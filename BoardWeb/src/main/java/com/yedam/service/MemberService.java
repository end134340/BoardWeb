package com.yedam.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.vo.MemberVO;

public interface MemberService {
	//업무 프로세스 (Model???)
	
	//로그인 절차
	MemberVO login(String id, String pw);
	//컨트롤러에서 서비스 요청 => 서비스 인터페이스를 구현한 클래스 기능 실행?
	
	//회원가입 (insert: db용어 add: 업무상의 용어)
	boolean addMember(MemberVO member);
}
