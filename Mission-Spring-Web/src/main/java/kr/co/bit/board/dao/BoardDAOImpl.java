package kr.co.bit.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bit.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {


	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<BoardVO> selectAll() {
		List<BoardVO> list 
		= sqlSession.selectList(
				"kr.co.bit.board.dao.BoardDAO.selectAllBoard");
		return list;
	}

	public BoardVO selectOne(int no) {
		return sqlSession.selectOne("kr.co.bit.board.dao.BoardDAO.selectBoardByNo", no);
	}

	public void insert(BoardVO board) {
		sqlSession.insert("kr.co.bit.board.dao.BoardDAO.insertBoard", board);
	}

	public void update(BoardVO board) {
		// TODO Auto-generated method stub

	}

	public void delete(int no) {
		// TODO Auto-generated method stub

	}

}
