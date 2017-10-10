package kr.co.bit.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bit.board.service.BoardService;
import kr.co.bit.board.vo.BoardVO;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("/list.do")
	public ModelAndView list(){
		List<BoardVO> boardList = service.selectAllBoard();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");
		//위주소로포워딩
		mav.addObject("boardList",boardList);
		//객체공유
		return mav;
	}
	//스프링에서는 굳이 mav형식으로 리턴해주지않아도 자기경로밑에jsp파일로자동으로 보내줌.(이름과경로가같아서void여도에러가안남)
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public void writeForm(Model model){
		
		/*BoardVO board = new BoardVO();
		board.setTitle("aaa");
		model.addAttribute("boardVO",board);*/
		
		//HttpServletRequest request
		//request.setAttribute("boardVO",new BoardVO());
		model.addAttribute("boardVO",new BoardVO());
		//write에서입력받은값을넣어저장시킬객체를미리저장.
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(@Validated BoardVO boardVO,BindingResult result){
		
		if(result.hasErrors()){
			//true
			return "board/write";
		}
		
		System.out.println(boardVO);
		service.insertBoard(boardVO);
		return"redirect:/board/list.do";
	//스프링에서는 pagecontext쓸필요없음.
	}
	
	@RequestMapping("/detail.do")
	public void detail(@RequestParam("no") int no){
		BoardVO board = service.selectBoardByNo(no);
		System.out.println(board);
	}
	
	@RequestMapping("/{no}/detail.do")
	public void detail01(@PathVariable int no){
		BoardVO board = service.selectBoardByNo(no);
		System.out.println(board);
	}
	
	@RequestMapping("/detail/{no}")
	public void detail02(@PathVariable int no){
		BoardVO board = service.selectBoardByNo(no);
		System.out.println(board);
	}
}
