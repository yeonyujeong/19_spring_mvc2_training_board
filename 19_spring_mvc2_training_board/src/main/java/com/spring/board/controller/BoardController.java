package com.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.board.dto.BoardDTO;
import com.spring.board.service.BoardService;

@Controller	//컨트롤러임을 명시(controller bean에 등록시키는 작업)
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public String main() {
		return "board/main";
	}
	
	
	// value에는 접근 url주소를 명시 , method는 접근 요청 타입을 명시 ( 생략할경우 GET,POST 모두 처리 )
	@RequestMapping(value="/boardWrite" ,  method=RequestMethod.GET)
	public String boardWrite() {
		return "board/bWrite";	// servlet-context.xml에 명시된대로 포워딩 시켜줄 jsp파일을 작성해준다.
	}
	
	@RequestMapping(value="/boardWrite" ,  method=RequestMethod.POST)
	public String boardWriteAction(BoardDTO bdto) {
		boardService.insertBoard(bdto);
		return "redirect:boardList"; // redirect:url에 해당하는 곳으로 이동한다.
	}
	
	@RequestMapping(value="/boardList")
	public String boardList(Model model) {
		
		model.addAttribute("boardList" , boardService.getBoardList());
		
		return "board/bList";
	}
	
	@RequestMapping(value="/boardInfo")
	public String boardInfo(@RequestParam("num") int num , Model model) {

		model.addAttribute("bdto" , boardService.getOneBoard(num));
		return "board/bInfo";
	}
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.GET)
	public String boardUpdateForm(@RequestParam("num") int num , Model model) {
		model.addAttribute("bdto" , boardService.getOneBoard(num));
		return "board/bUpdate";
	}
	
	
	@RequestMapping(value="/boardUpdate" , method=RequestMethod.POST)
	public String boardUpdate(BoardDTO bdto , Model model) {
		
		if(boardService.updateBoard(bdto)) model.addAttribute("success" , true);
		else 							   model.addAttribute("success" , false);
		return "board/bUpdatePro";
	}
	
	
	@RequestMapping(value="/boardDelete" , method=RequestMethod.GET)
	public String boardDeleteForm(@RequestParam("num") int num, Model model) {
		model.addAttribute("bdto" , boardService.getOneBoard(num));
		return "board/bDelte";
	}
	
	
	@RequestMapping(value="/boardDelete" , method=RequestMethod.POST)
	public String boardDelete(BoardDTO bdto , Model model) {
		
		if(boardService.deleteBoard(bdto)) model.addAttribute("success" , true);
		else 							   model.addAttribute("success" , false);
		
		return "board/bDeletePro";
	}
	
}
