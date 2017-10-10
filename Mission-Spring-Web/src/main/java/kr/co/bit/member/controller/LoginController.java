package kr.co.bit.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.bit.member.service.MemberService;
import kr.co.bit.member.vo.MemberVO;

@SessionAttributes("userVO")
@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String loginForm(){
		return "login/login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(MemberVO member,HttpSession session,Model model){
		MemberVO userVO = service.login(member);
		
		if(userVO==null){
			return "login/login";
		}
		//로그인성공
		//session.setAttribute("userVO",userVO);
		model.addAttribute("userVO",userVO);
		//model은원래request영역에 저장되기때문에 요청이끝나면없어지는데@SessionAttributes("userVO")어노테이션을사용하면 model객체중해당이름을사용하는객체는session영역에저장됨.
		//@SessionAttributes를이용해서 저장된 객체는 session.invalidate()삭제할수없다.->sessionStatus.setComplete();로삭제해야함.
		
		return"redirect:/";
	}
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session,SessionStatus sessionStatus){
		sessionStatus.setComplete();
		return"redirect:/";
	}
}
