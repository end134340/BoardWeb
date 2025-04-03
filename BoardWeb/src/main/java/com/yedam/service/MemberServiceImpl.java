package com.yedam.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

//업무(service 구현 객체)
public class MemberServiceImpl implements MemberService {
	// 컨트롤러가 요청한 기능 구현??하는???클래스?
	// 컨트롤에서는 직접 mapper를 사용해서 기능을 처리하지 않고 service를 호출해서? 거기서 받아와서 처리...?

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

	@Override
	public MemberVO login(String id, String pw) {
		return mapper.selectMember(id, pw);
	}

	@Override
	public boolean addMember(MemberVO member) {
		//insert는 int로 처리된 건수를 반환해주기 때문에 1건이 정상적으로 입력되었는지를 리턴해줌
		return mapper.insertMember(member) == 1;
	}

}
