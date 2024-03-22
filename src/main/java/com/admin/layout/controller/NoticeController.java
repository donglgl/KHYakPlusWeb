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


import com.admin.layout.service.userService;
import com.admin.layout.vo.Notice;
import com.admin.layout.repository.NoticeRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller

// 로그인 할때 필요한듯
@SessionAttributes(value ="login")
public class NoticeController {

	
	// mapper, DAO 생략용 (원래는 Service가서 해야되는듯)
	@Autowired
	private NoticeRepository noticeRepository;
	
	
	// 로그인 서비스용??
	@Autowired
	private userService userService;
	

		
		
	
	
	
	
	// 메인 & 페이징
	@GetMapping("/notice/list")
	public String paging(Model model, 
			             @RequestParam(defaultValue="0") int page) {
                                         //@RequestParam > 페이지 값 0으로 설정			
		
		
		log.info("paging 메인");
		
		// 페이지 사이즈
		int pageSize = 10;
				
		// 페이지 내림차순
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("noticeNum").descending());
		
		// 페이지로 부터 게시글 목록을 가져오기 
		Page<Notice> list = noticeRepository.findAll(pageable);	
		// log.info("{}",list);
		
		
		// getContent에 리스트형태로 저장
		model.addAttribute("lists", list.getContent());
		// log.info("{}",list.getContent());
		
		
		// page변수를 이용해서 페이징 버튼을 만들 것!
		model.addAttribute("page",list);
		
		return "board/noticeList";
	}
	
	
	
	
	// 검색창
	@PostMapping("/notice/search")
	public String search(Model model,
			            @RequestParam(value="search",required=false) String keyword,
	                    @RequestParam(defaultValue="0") int page ,
	                    @RequestParam(value="category",required=false) String category){
		
		log.info("search 실행");
		
	    // 페이지네이션 설정: 사용자가 선택한 페이지 번호를 반영		
	    Pageable pageable = PageRequest.of(page, 10, Sort.by("noticeNum").descending());

	    Page<Notice> searchResultPage;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	
	        searchResultPage = noticeRepository.findByNoticeTitleContaining(keyword, pageable);
	        
	    } else {
	    	
	        if((category !=null && !category.isEmpty())  && !category.equals("all")) {
	        	searchResultPage = noticeRepository.findByNoticeTitleContaining(category, pageable);
	        	
	        }else {
	        	 searchResultPage = noticeRepository.findAll(pageable);
	        }
	    }

	    model.addAttribute("lists", searchResultPage.getContent());
	    model.addAttribute("page", searchResultPage);	    
	    model.addAttribute("searchKeyword", keyword); // 검색했을때 
	    model.addAttribute("noticeCate", category); // 카테고리까지 추가
	    model.addAttribute("loginMember", "admin"); // admin값을  loginMember에 넣는다
	       
	    return "board/noticeList";
	}

	
	
	
	
	// 카테고리
	@PostMapping("/notice/cate")
	public String cate(Model model, 
	                   @RequestParam(defaultValue="0") int page, 
	                   @RequestParam(defaultValue="all") String category) {
		
		log.info("카테고리 실행");
	    int pageSize = 10;    
	    Pageable pageable = PageRequest.of(page, pageSize, Sort.by("noticeNum").descending());
	    
	    
	    Page<Notice> list;

	    if("all".equals(category)) {
	        list = noticeRepository.findAll(pageable);
	        
	    } else {
	        list = noticeRepository.findByNoticeCateContaining(category, pageable);    
	    } 	    
	    
	    model.addAttribute("noticeCate", category); // 카테고리 페이징용
	    model.addAttribute("lists", list.getContent());
	    model.addAttribute("page", list);
	    
	    return "board/noticeList";
	}
	

	

	
	// 상세페이지 이동
	@GetMapping("/notice/detail/{noticeNum}")
	public String detail(@PathVariable("noticeNum") Integer noticeNum, 
			             Model model) {
	    
		log.info("detail 실행", noticeNum);

	    // 데이터베이스에서 게시글 정보 꺼내기
	    Notice notice = noticeRepository.findById(noticeNum).orElse(null);		    
	    
	    
	    
	    // 조회수 증가	 		
	    notice.setNoticeHit(notice.getNoticeHit() +1);
	    noticeRepository.save(notice);
	    
	 	//log.info("{}",notice);
	 	//log.info("{}",notice.getNoticeHit());
	    
	        	    
	    
	    // 모델에 값 저장
	    model.addAttribute("notice", notice);	   

	    // 뷰 페이지로 전달
	    return "board/noticeDetail";
	}

	
	
	
	
	 // 삭제하기 (service + restContoroller)
	 @GetMapping("/notice/delete/{noticeNum}")
		public String delete(@PathVariable Integer noticeNum) {

			//log.info("컨트롤러의 delete()메서드 실행");
			//log.info("boardNum = " + boardNum);

		     Notice target = noticeRepository
							.findById(noticeNum)
							.orElse(null);

			
			// 삭제할 데이터를 가져와서 null이 아니면 삭제하기
			if (target != null) {
				
				noticeRepository.delete(target);
			}

			return "redirect:/notice/list";
		}
	 
	 
	
	 
	 
	 

	  // 글쓰기 페이지 단순 이동	  
	  @GetMapping("/notice/write") 
	  public String writeGo(){
	  
	          return "/board/noticeWrite"; // noticeWrite.html로 이동
	  }

	  
	  
	  // 공지 쓰기
      @PostMapping("/notice/write")
      public String write(Notice notice,
    		              RedirectAttributes redirectAttributes) {
    	 
    	 
    	// 관리자 아이디 값 등록
    	notice.setAdminId("관리자");
    	
    	
    	// 시간 등록
    	notice.setNoticeDate(Date.valueOf(
			               LocalDateTime.now()
			               .toLocalDate()));
	    	    
    	
    	noticeRepository.save(notice);
        
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 등록");
        
        return "redirect:/notice/list";
    }
      
	 
	  
	  
	
} //controller 끝



