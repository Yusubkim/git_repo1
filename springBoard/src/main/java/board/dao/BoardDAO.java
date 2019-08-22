package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.bean.BoardDTO;

@Repository
public class BoardDAO {	
	private @Autowired SqlSessionTemplate sqlSession;
	// 데이터 저장
	public int boardWrite(BoardDTO boardDTO) {
		return sqlSession.insert("mybatis.board.boardWrite", boardDTO);
	}
	
	public List<BoardDTO> boardList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("mybatis.board.boardList", map);
	}
	// 1줄 검색
	public BoardDTO boardView(int seq) {
		return sqlSession.selectOne("mybatis.board.boardView", seq);
	}
	// 총 글수 구하기
	public int getTotalA() {
		return sqlSession.selectOne("mybatis.board.getTotalA");
	}
	// 조회수 증가
	public int updateHit(int seq) {
		return sqlSession.update("mybatis.board.updateHit", seq);
	}
	// 글삭제
	public int boardDelete(int seq) {
		return sqlSession.delete("mybatis.board.boardDelete", seq);
	}
	
	public int boardModify(BoardDTO boardDTO) {
		return sqlSession.update("mybatis.board.boardModify", boardDTO);		
	}
}





















