package com.yedam.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;

public class Test {
	public static void main(String[] arsg) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		
		EventMapper mapper = sqlSession.getMapper(EventMapper.class);
		
		List<Map<String, Object>> list = mapper.selectWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		System.out.println(json);

		
//		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
//		MemberVO member = mapper.selectMember("user01", "1111");

//		MemberService svc = new MemberServiceImpl();

//		MemberVO member = svc.login("user01", "1111");

//		System.out.println(member.toString());

//		SqlSession sqlSession = DataSource.getInstance().openSession(true);
//		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
//
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBoardNo(234);
//		rvo.setReply("매퍼 테스트 중~~");
//		rvo.setReplyer("kanu");
//
//		List<Map<String, Object>> list = mapper.selectListForDT(234);
//		List<List<Object>> slist = new ArrayList<List<Object>>();
//		for (int i = 0; i < list.size(); i++) {
//			List<Object> ilist = new ArrayList<Object>();
//			ilist.add(list.get(i).get("REPLY_NO"));
//			ilist.add(list.get(i).get("REPLY"));
//			ilist.add(list.get(i).get("REPLYER"));
//			slist.add(ilist);
//
//		}
//		// {"data":[[], [], [], []]
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("data", list);

//		int cnt = mapper.insertReply(rvo);

//		if(cnt > 0) {
//			System.out.println("댓글이 등록되었습니다.");
//		}

//		List<ReplyVO> list = mapper.selectList(234);
//		for(ReplyVO reply : list) {
//			System.out.println(reply.toString());
//		}

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
