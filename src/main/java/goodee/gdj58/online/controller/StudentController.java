package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Paper;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;



@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	@Autowired TestService testService;
	
	//테스트 리스트
	@GetMapping("/student/studentTestList")
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
		return "student/studentTestList";
		
	}
	
	//점수 
	@GetMapping("/student/studentTestScore")
	public String stduentTestList(Model model
					,@RequestParam(value="testNo") int testNo) {
		int answerCnt = studentService.answerCnt(testNo);
		
		model.addAttribute("answerCnt", answerCnt);
		return "student/studentTestScore";
	}
	
	// 답안지 제출 (paper등록)
	@GetMapping("/student/addPaper")
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
		return "student/addPaper";
		}
	@PostMapping("/student/addPaper")
	public String addPaper(@RequestParam(value="questionNo") int[] questionNo
							, @RequestParam(value="answer") int[] answer
							, @RequestParam(value="testNo") int testNo
							, @RequestParam(value="studentNo") int studentNo) {

		
		// 답안지 제출 및 채점
		int questionCnt = questionNo.length; // 문항 갯수
		Paper[] paper = new Paper[questionCnt]; 
		for(int i=0; i<questionCnt; i++) {
			paper[i] = new Paper();
			paper[i].setQuestionNo(questionNo[i]);
			paper[i].setAnswer(answer[i]);
			paper[i].setTestNo(testNo);
			paper[i].setStudentNo(studentNo);
			int answerOx = studentService.getQuestionOx(questionNo[i]); // 실제 문제의 정답
			if(answerOx == paper[i].getAnswer()) {
				// 제출답안과 실제정답이 일치할 경우
				paper[i].setAnswerOx("정답");
			} else {
				paper[i].setAnswerOx("오답"); 
			}
			int addPaper = studentService.addPaper(paper[i]);
		}
		
		return "student/studentOne";
	}
	
	// student 홈 
	@GetMapping("/student/studentOne")
	public String StudentOne() {
		return "student/studentOne";
	}
	
	// pw수정 폼
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session) {
		//로그인 후 호출 가능
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		return "student/modifyStudentPw";
	}
	// pw수정 액션 
	@PostMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		
		studentService.updateStudentPw(loginStudent.getStudentNo(), oldPw, newPw);
		
		return "redirect:/employee/studentList";
	}
	//로그인 
	@GetMapping("/loginStudent")
	public String loginStudent() {
		return "login";
	}
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent = studentService.login(student);
		
		session.setAttribute("loginStudent", resultStudent);
		return "redirect:/student/studentOne";
	}
	

	
	//삭제 
	@GetMapping("employee/removeStudent")
	public String removeStudent(HttpSession session ,@RequestParam("studentNo") int studentNo) {


		studentService.removeStudent(studentNo);
		return "redirect:/employee/studentList";
	}
	
	//입력
	@GetMapping("employee/addStudent")
	public String addStudent() {

		return "employee/addStudent";
	}
	@PostMapping("employee/addStudent")
	public String addStudent(Model model,Student student) {

		
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된Id");
			return "employee/addStudent";
		}
		
		int row = studentService.addStudent(student);
		// row == 1 이면 입력성공
		return "redirect:/employee/studentList"; 
	}
	
	// 리스트 
	@GetMapping("employee/studentList")
	public String studentList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue = "") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		//사원만 볼수있음
		int lastPage = (int)Math.ceil((double)studentService.lastPage(searchWord)/(double)rowPerPage);
		int startPage = (currentPage/rowPerPage)*10 +1;
		int endPage = (currentPage/rowPerPage)*10 + 10;
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		if(lastPage<1) {
			lastPage= currentPage;
		}
	
					
		
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "student/studentList";
		
	}
}
