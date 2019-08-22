package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.bean.BoardDTO;

@Controller
public class BoardController {
	private @Autowired BoardService service;
	
	@RequestMapping(value="/board/boardWriteForm.jj")
	public ModelAndView boardWriteForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../board/boardWriteForm.jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardWrite.jj")
	public ModelAndView boardWrite(HttpServletRequest request) {
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("memName");
		String id = (String)session.getAttribute("memId");
		// DB 처리
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setId(id);
		boardDTO.setName(name);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		int su = service.boardWrite(boardDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("su", su);
		modelAndView.setViewName("../board/boardWrite.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardList.jj")
	public ModelAndView boardList(HttpServletRequest request) {
		int pg = Integer.parseInt(request.getParameter("pg"));
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		List<BoardDTO> list = service.boardList(startNum, endNum);
		
		int totalA = service.getTotalA();	// 총글수
		int totalP = (totalA + 4) / 5;	// 총페이지수
		
		int startPage = (pg-1)/3*3 + 1;	
		int endPage = startPage + 2;
		if(totalP < endPage) endPage = totalP;
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("list", list);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("totalP", totalP);
		
		modelAndView.setViewName("../board/boardList.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardView.jj")
	public ModelAndView boardView(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		// 조회수 증가
		service.updateHit(seq);
		BoardDTO boardDTO = service.boardView(seq);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("boardDTO", boardDTO);
		
		modelAndView.setViewName("../board/boardView.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardModifyForm.jj")
	public ModelAndView boardModifyForm(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		BoardDTO boardDTO = service.boardView(seq);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("boardDTO", boardDTO);
		
		modelAndView.setViewName("../board/boardModifyForm.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardModify.jj")
	public ModelAndView boardModify(HttpServletRequest request) {
		String pg = request.getParameter("pg");
		int seq = Integer.parseInt(request.getParameter("seq"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		// DB
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setSeq(seq);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		int su = service.boardModify(boardDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("su", su);
		
		modelAndView.setViewName("../board/boardModify.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/board/boardDelete.jj")
	public ModelAndView boardDelete(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		// DB
		int su = service.boardDelete(seq);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("su", su);
		
		modelAndView.setViewName("../board/boardDelete.jsp");
		
		return modelAndView;
	}
}
