package com.yedam.mapper;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.MemberVO;

public interface MemberMapper {
	
	//멤버 id와 pw를 받아서 하나 조회 
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);
	//@Param("") < "" 사이에 적은 이름을 mapper.xml에서 이 이름의 변수로 사용하겠다는 의미...? 넘길 값이 2개 이상일 때 xml에서 사용할 파라미터 이름 지정하는 것 
	
	int insertMember(MemberVO member);
}
