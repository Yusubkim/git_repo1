package member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;

@Controller
public class MemberController {
	private @Autowired MemberService service;
	
	@RequestMapping(value="/member/loginForm.jj")
	public ModelAndView loginForm() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/loginForm.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/login.jj")
	public ModelAndView login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		String name = service.login(id, pwd);
		
		ModelAndView modelAndView = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(name != null) { 	
			session.setAttribute("memName", name);
			session.setAttribute("memId", id);
			
			modelAndView.setViewName("redirect:../member/loginOk.jj");
		} else { 
			modelAndView.setViewName("redirect:../member/loginFail.jj");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/member/loginOk.jj")
	public ModelAndView loginOk() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/loginOk.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/loginFail.jj")
	public ModelAndView loginFail() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/loginFail.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/logout.jj")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/logout.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/writeForm.jj")
	public ModelAndView writeForm() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/writeForm.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/checkId.jj")
	public ModelAndView checkId(@RequestParam("id") String id) {
		String name = service.isExistId(id);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("id", id);
		modelAndView.addObject("name", name);
		
		modelAndView.setViewName("../member/checkId.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/write.jj")
	public ModelAndView write(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String addr = request.getParameter("addr");
		
		// DB 처리
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setAddr(addr);
		
		int su = service.write(memberDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("su", su);
		
		modelAndView.setViewName("../member/write.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/deleteForm.jj")
	public ModelAndView deleteForm() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("../member/deleteForm.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/delete.jj")
	public ModelAndView delete(HttpSession session) {
		String id = (String)session.getAttribute("memId");
		int su = service.delete(id);
		session.invalidate();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("su", su);
		
		modelAndView.setViewName("../member/delete.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/modifyForm.jj")
	public ModelAndView modifyForm(HttpSession session) {
		String id = (String)session.getAttribute("memId");
		MemberDTO memberDTO = service.selectOne(id);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("memberDTO", memberDTO);
		
		modelAndView.setViewName("../member/modifyForm.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/modify.jj")
	public ModelAndView modify(HttpServletRequest request) {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String addr = request.getParameter("addr");
		
		// DB 처리
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setAddr(addr);
		
		int su = service.modify(memberDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("su", su);
		
		modelAndView.setViewName("../member/modify.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/member/memberList.jj")
	public ModelAndView memberList(@RequestParam("pg") int pg) {
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		List<MemberDTO> list = service.selectList(startNum, endNum);
		
		int totalMember = service.getTotalMember();	// 총 회원수
		int totalP = (totalMember + 4) / 5;
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(totalP < endPage) endPage = totalP;
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("list", list);
		modelAndView.addObject("totalP", totalP);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		
		modelAndView.setViewName("../member/memberList.jsp");
		
		return modelAndView;
	}
}
