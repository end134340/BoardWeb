package com.yedam.test;

import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class Test {
	public static void main(String[] arsg) {
//		SqlSession sqlSession = DataSource.getInstance().openSession();
//		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
//		MemberVO member = mapper.selectMember("user01", "1111");
		
		MemberService svc = new MemberServiceImpl();
		
		MemberVO member = svc.login("user01", "1111");

		System.out.println(member.toString());

//		BoardVO board = new BoardVO();
//		board.setTitle("1시간남음");
//		board.setContent("와~~~");
//		board.setWriter("newbie");
//		board.setBoardNo(6);

		// 옛날 방식
//		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
		// insert, update, delete는 반환되는 값이 int타입으로 지정 되어 있음.
//			int r = sqlSession.insert("com.yedam.mapper.BoardMapper.insertBoard", board); // insert("mapper insert에 설정해둔 id값", 파라미터)
//			int r = sqlSession.update("com.yedam.mapper.BoardMapper.updateBoard", board);
//			int r = sqlSession.delete("com.yedam.mapper.BoardMapper.deleteBoard", board);
//			if(r == 1) {
//				System.out.println("등록에 성공했습니다.");
//				sqlSession.commit();
//			}else {
//				System.out.println("등록에 실패했습니다.");
//			}
//			List<BoardVO> list = sqlSession.selectList("com.yedam.mapper.BoardMapper.selectBoard");
//			for(BoardVO brd : list) {
//				System.out.println(brd.toString());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 현재??방식
//		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
//
//			int r = mapper.insertBoard(board);
//			if (r == 1) {
//				System.out.println("실행에 성공했습니다.");
//				sqlSession.commit();
//			}
//			List<BoardVO> list = mapper.selectBoard(1);
//			for (BoardVO brd : list) {
//				System.out.println(brd.toString());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
