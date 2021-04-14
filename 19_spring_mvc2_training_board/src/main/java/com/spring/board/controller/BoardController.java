package com.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.board.dto.BoardDTO;
import com.spring.board.service.BoardService;

@Controller	//컨트롤러임을 명시(controller bean에 등록시키는 작업)
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	// value에는 접근 url주소를 명시 , method는 접근 요청 타입을 명시 ( 생략할경우 GET,POST 모두 처리 )
	@RequestMapping(value="/boardWrite" ,  method=RequestMethod.GET)
	public String boardWrite() {
		return "board/bWrite";	// servlet-context.xml에 명시된대로 포워딩 시켜줄 jsp파일을 작성해준다.
	}
	
	@RequestMapping(value="/boardWrite" ,  method=RequestMethod.POST)
	public String boardWriteAction(BoardDTO bdto) {
		boardService.insertBoard(bdto);
		return "board/bList";
	}
	
	
	
}
