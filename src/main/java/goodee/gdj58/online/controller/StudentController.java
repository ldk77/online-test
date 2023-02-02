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
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;



@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	
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
		return "student/loginStudent";
	}
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent = studentService.login(student);
		
		session.setAttribute("loginStudent", resultStudent);
		return "redirect:/student/modifyStudentPw";
	}
	
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/student/loginStudent";
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
