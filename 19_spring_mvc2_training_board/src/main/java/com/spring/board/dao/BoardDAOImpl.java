package com.spring.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDTO;

@Repository		// DAO( 데이터 접근 객체 )임을 명시하여야 한다. (DAO bean으로 등록)
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(BoardDTO bdto) {
		sqlSession.insert("com.spring.mapper.BoardMapper.insertBoard" , bdto);
	}

}
