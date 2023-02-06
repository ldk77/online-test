package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;


@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// 보기 입력
	public String addExample() {
		return "teacher/addExample";
	}
	@PostMapping("/teacher/addExample")
	public String addExample(Model model,Example example) {	
		int row = teacherService.addExample(example);
		// row == 1 이면 입력성공
		return "redirect:/test/testList"; 
	}
	// 질문지 입력
	@GetMapping("teacher/addQuetion")
	public String addQuetion() {
		return "teacher/addQuetion";
	}
	@PostMapping("/teacher/addQuetion")
	public String addQuetion(Model model,Question question) {	
		int row = teacherService.addQuestion(question);
		// row == 1 이면 입력성공
		return "redirect:/test/testList"; 
	}
	
	// 시험수정 폼
	@GetMapping("/teacher/modifyTest")
	public String modifyTest(Model model
							,@RequestParam(value="testNo",required = true) int testNo
							, @RequestParam(value="testTitle",required = true) String testTitle
							, @RequestParam(value="testDate",required = true) String testDate) {
		model.addAttribute("testNo", testNo);
		model.addAttribute("testTitle", testTitle);
		model.addAttribute("testDate", testDate);
		return "teacher/modifyTest";
	}
	// pw수정 액션 
	@PostMapping("/teacher/modifyTest")
	public String modifyTest( @RequestParam(value="testNo",required = true) int testNo
								, @RequestParam(value="testTitle",required = true) String testTitle
								, @RequestParam(value="testDate",required = true) String testDate) {
		
		teacherService.updateTest(testNo, testTitle, testDate);		
		return "redirect:/test/testList";
	}
	
	// 시험지 입력
	@GetMapping("teacher/addTest")
	public String addTest() {
		return "teacher/addTest";
	}
	@PostMapping("/teacher/addTest")
	public String addTest(Model model,Test test) {	
		int row = teacherService.addTest(test);
		// row == 1 이면 입력성공
		return "redirect:/test/testList"; 
	}
	
	// student 홈 
	@GetMapping("/teacher/teacherOne")
	public String StudentOne() {
		return "teacher/teacherOne";
	}
	
	// pw수정 폼
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw() {
		return "teacher/modifyTeacherPw";
	}
	// pw수정 액션 
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		
		teacherService.updateTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		
		return "redirect:/employee/teacherList";
	}
	//로그인 
	@GetMapping("/loginTeacher")
	public String loginTeacher(HttpSession session) {
		//이미 로그인 중이라면 redirect:/employee/empList
		return "teacher/loginTeacher";
	}
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher = teacherService.login(teacher);
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/teacher/teacherOne";
	}
	
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/teacher/loginTeacher";
	}
	
	//삭제 
	@GetMapping("employee/removeTeacher")
	public String removeTeacher(@RequestParam("teacherNo") int teacherNo) {
		teacherService.removeTeacher(teacherNo);
		return "redirect:/employee/teacherList";
	}
	
	//입력
	@GetMapping("employee/addTeacher")
	public String addTeacher() {
		return "employee/addTeacher";
	}
	@PostMapping("/employee/addTeacher")
	public String addTeacher(Model model,Teacher teacher) {

		
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된Id");
			return "teacher/addTeacher";
		}
		
		int row = teacherService.addTeacher(teacher);
		// row == 1 이면 입력성공
		return "redirect:/employee/teacherList"; 
	}
	
	// 리스트 
	@GetMapping("/employee/teacherList")
	public String teacherList(Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue = "") String searchWord) {
			// int currentPage = request.getParameter("currentPage");
		int lastPage = (int)Math.ceil((double)teacherService.lastPage(searchWord)/(double)rowPerPage);
		int startPage = (currentPage/rowPerPage)*10 +1;
		int endPage = (currentPage/rowPerPage)*10 + 10;
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		if(lastPage<1) {
			lastPage= currentPage;
		}
					
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "teacher/teacherList";
		
	}
}
