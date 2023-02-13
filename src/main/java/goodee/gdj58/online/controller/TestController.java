package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	
	//css 출력
	@GetMapping("/test/new")
	public String index() {
		return "/test/new";
	}
	
	// Test 리스트 출력 
	@GetMapping("/test/testOne")
	public String testOne(Model model
							, @RequestParam(value="testNo") int testNo) {
		List<Map<String,Object>> list = testService.getExampleList(testNo);
		Test thisTest = testService.testOne(testNo);
		int ttlQstCnt = list.size() / 4;
		int lastQuestion = testService.getLastQuestion();
	
		model.addAttribute("list", list);
		model.addAttribute("ttlQstCnt", ttlQstCnt);
		model.addAttribute("thisTest", thisTest);
		model.addAttribute("lastQuestion", lastQuestion);
		return "test/testOne";
	}
	
	
	// Test 리스트 출력 
		@GetMapping("/test/testList")
		public String testList(Model model
				, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
				, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage
				, @RequestParam(value="searchWord", defaultValue = "") String searchWord) {
				// int currentPage = request.getParameter("currentPage");
			int lastPage = (int)Math.ceil((double)testService.cntTest(searchWord)/(double)rowPerPage);
			int startPage = (currentPage/rowPerPage)*10 +1;
			int endPage = (currentPage/rowPerPage)*10 + 10;
			if(endPage > lastPage) {
				endPage = lastPage;
			}
			if(lastPage<1) {
				lastPage= currentPage;
			}
						
			List<Test> list = testService.getTestList(currentPage, rowPerPage, searchWord);
			model.addAttribute("list", list);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("lastPage", lastPage);
			
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			return "test/testList";
			
		}
		
}
