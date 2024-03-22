package com.admin.layout.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.time.LocalDateTime;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.layout.repository.ReplyRepository;
import com.admin.layout.service.userService;
import com.admin.layout.repository.BoardRepository;
import com.admin.layout.vo.Board;
import com.admin.layout.vo.Reply;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

// 로그인 할때 필요한듯
@SessionAttributes(value ="login")
public class BoardController {

	
	// mapper, DAO 생략용 (원래는 Service가서 해야되는듯)
	@Autowired
	private BoardRepository boardRepository;
	
	// 댓글용
	@Autowired
	private ReplyRepository replyRepository;
	
	// 로그인 서비스용 
	@Autowired
	private userService userService;
	
	
	// 글쓰기 페이지 단순 이동	  
	@GetMapping("/faq") 
	public String faq(){
		  
	    return "/board/faq"; // boardWrite.html로 이동
	}
		
	
	
	
	// 디비 연결 확인용
//	@GetMapping("/")
//	public String boardList (Model model) {
//			
//		// 게시글을 가져오는 명령문!
//		List<Board> list = boardRepository.findAll();	
//	
//        // 숫자 반대로(위에 라이브러리 추가)
//		Collections.sort(list, Comparator.comparing(Board::getBoardDate).reversed());
//				
//		log.info("{}",list);
//		model.addAttribute("lists", list);
//		
//		return "boardList";
//	}
	
	
	

	
	
	// 메인 & 페이징
	@GetMapping("/board/list")
	public String paging(Model model, 
			             @RequestParam(defaultValue="0") int page) {
                                         //@RequestParam > 페이지 값 0으로 설정			
		
		
		log.info("paging 메인");
		// log.info("boardList() ");
		
		// 페이지 사이즈
		int pageSize = 10;
				
		// 페이지 내림차순
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("boardNum").descending());
		
		// 페이지로 부터 게시글 목록을 가져오기 
		Page<Board> list = boardRepository.findAll(pageable);	
		// log.info("{}",list);
		
		
		// getContent에 리스트형태로 저장
		model.addAttribute("lists", list.getContent());
		// log.info("{}",list.getContent());
		
		
		// page변수를 이용해서 페이징 버튼을 만들 것!
		model.addAttribute("page",list);
		
		return "board/boardList";
	}
	
	
	
	
	// 검색창
	@PostMapping("/board/search")
	public String search(Model model,
			            @RequestParam(value="search",required=false) String keyword,
	                    @RequestParam(defaultValue="0") int page ,
	                    @RequestParam(value="category",required=false) String category){
		
		log.info("search 실행");
	    // 페이지네이션 설정: 사용자가 선택한 페이지 번호를 반영
	    Pageable pageable = PageRequest.of(page, 10, Sort.by("boardNum").descending());

	    Page<Board> searchResultPage;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	
	        searchResultPage = boardRepository.findByBoardTitleContaining(keyword, pageable);
	   
	    } else {
	    	
	        if((category !=null && !category.isEmpty())  && !category.equals("all")) {
	        	
	        	searchResultPage = boardRepository.findByboardCateContaining(category, pageable);
	        
	        }else {
	        	
	        	 searchResultPage = boardRepository.findAll(pageable);
	        }
	    }
	    
	    String admin = "admin";
	    
	    model.addAttribute("lists", searchResultPage.getContent());
	    model.addAttribute("page", searchResultPage);	    
	    model.addAttribute("searchKeyword", keyword); // 검색했을때 
	    model.addAttribute("boardCate", category); // 카테고리까지 추가
	    model.addAttribute("loginMember", admin); // 살려줘야함...나중에 이용
	       
	    return "board/boardList";
	}
		
	
	
	
	
	// 카테고리
	@PostMapping("/board/cate")
	public String cate(Model model, 
	                   @RequestParam(defaultValue="0") int page, 
	                   @RequestParam(defaultValue="all") String category) {
		
		log.info("카테고리 실행");
	    int pageSize = 10;    
	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by("boardNum").descending());
	    
	    
	    Page<Board> list;

	    if("all".equals(category)) {
	        list = boardRepository.findAll(pageable);
	        
	    } else {
	        list = boardRepository.findByboardCateContaining(category, pageable);    
	    } 	    
	    
	    model.addAttribute("boardCate", category); // 카테고리 페이징용
	    model.addAttribute("lists", list.getContent());
	    model.addAttribute("page", list);
	    
	    return "board/boardList";
	}
	
	
	
	
	
	
	
	// 상세페이지 이동
	@GetMapping("/board/detail/{boardNum}")
	public String detail(@PathVariable("boardNum") Integer boardNum, 
			             Model model) {
	    
		log.info("detail() 호출 : {}", boardNum);

	    // 데이터베이스에서 게시글 정보 꺼내기
	    Board board = boardRepository.findById(boardNum).orElse(null);
	    
	    
	 // 수정된 메소드를 사용하여 댓글 조회
	 List<Reply> replyList = 	     
	 replyRepository.findByBoardNumAndAdminNum(boardNum, board.getMemNum());        
	    
	    
	    // 모델에 값 저장
	    model.addAttribute("board", board);
	    model.addAttribute("replyList", replyList);

	    // 뷰 페이지로 전달
	    return "board/boardDetail";
	}
	
	

	
	
	
	
	// 댓글 추가 
	@PostMapping("/board/reply")
	public String reply(
	        @RequestParam Integer boardNum, // 게시글 번호
            @RequestParam String adminNum, // 관리자 번호
	        @RequestParam String replyContent, // 댓글 내용
	        RedirectAttributes redirect) {
	    
	    // 게시글 가져오기
	    Board board = boardRepository.findById(boardNum).orElse(null);
	    
	    // 댓글 객체 생성 및 필드 설정
	    Reply reply = new Reply();
	    reply.setBoardNum(boardNum); // 게시글 번호
	    reply.setAdminNum(board.getMemNum()); // 관리자 번호
	    reply.setReplyContent(replyContent); // 댓글 내용
	    reply.setReplyDate(Date.valueOf(
		           LocalDateTime.now()
		           .toLocalDate())); // 현재 날짜로 설정
	    
	    // 댓글 저장
	    replyRepository.save(reply);
	    
	    // 게시글의 답변 상태를 '답변 완료'로 업데이트
	    board.setBoardCheck("답변완료");
	    boardRepository.save(board); // 수정: 게시글 상태 업데이트
	    
	    // 게시글 상세 페이지로 리다이렉트
	    redirect.addAttribute("boardNum", boardNum);
	    
	    return "redirect:/board/detail/{boardNum}";
	}
	
	
		 
	 
	
	 
	 // 수정하기1 (수정페이지 이동)	 
	 @GetMapping("/board/update/{boardNum}")
	 public String update(@PathVariable Integer boardNum, 
			            Model model) {
		 
	     Board board = boardRepository.findById(boardNum).orElse(null);
	     
	     if (board != null) {
	    	 
	         model.addAttribute("board", board);
	         
	         return "/board/boardUpdate"; 
	         
	     } else {
	         return "redirect:/board/list"; 
	     }
	 }
 
	 
	 
	// 수정하기2 (수정완료 후 상세페이지 이동)	 
	 @PostMapping("/board/update/{boardNum}")
	 public String updateGo(@PathVariable Integer boardNum,
			                 Board update, 
			                 RedirectAttributes redirectAttributes) {
		 
	     Board board = boardRepository.findById(boardNum).orElse(null);
	     
	     
	     if (board != null) {
	    	 
	    	 // 시간 수정
	    	 board.setBoardDate(Date.valueOf(
			                    LocalDateTime.now()
			                    .toLocalDate()));
	    	 
	    	 board.setBoardCate(update.getBoardCate());
	         board.setBoardTitle(update.getBoardTitle());
	         board.setBoardContent(update.getBoardContent());
	                 
	         boardRepository.save(board);
	         
	         redirectAttributes.addAttribute("boardNum", boardNum);
	         
	         return "redirect:/board/detail/" + boardNum; 
	         
	     } else {
	    	 
	         return "redirect:/board/list"; 
	     }
	 }
	
	 
	 
	 
	 
	 
	 // 삭제하기 (service + restContoroller)
	 @GetMapping("/board/delete/{boardNum}")
		public String delete(@PathVariable Integer boardNum) {

			//log.info("컨트롤러의 delete()메서드 실행");
			//log.info("boardNum = " + boardNum);

			Board target = boardRepository
							.findById(boardNum)
							.orElse(null);

			
			// 삭제할 데이터를 가져와서 null이 아니면 삭제하기
			if (target != null) {
				boardRepository.delete(target);
			}

			return "redirect:/board/list";
		}
	 
	 
	
	 
	 
	 

	  // 글쓰기 페이지 단순 이동	  
	  @GetMapping("/board/write") 
	  public String writeGo(){
	  
	          return "/board/boardWrite"; // boardWrite.html로 이동
	  }
	    
	  
	  
	  // 게시글 쓰기
      @PostMapping("/board/write")
      public String write(Board board,
    		              RedirectAttributes redirectAttributes,HttpSession session) {
    	 
    	// 로그인  값 등록 
    	String id = (String)session.getAttribute("id");
    	board.setMemId(id);
    	
    	// 시간 등록
	    board.setBoardDate(Date.valueOf(
			               LocalDateTime.now()
			               .toLocalDate()));
	    	    
    	
        boardRepository.save(board);
        
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록");
        
        return "redirect:/board/list";
    }
      
      
      
   
      
	 
	  
	  
	
} //controller 끝



