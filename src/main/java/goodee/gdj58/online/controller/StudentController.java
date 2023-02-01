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
		if(loginStudent == null) {
			return "redirect:/student/loginStudent";
		}
		return "student/modifyStudentPw";
	}
	// pw수정 액션 
	@PostMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		if(loginStudent == null) {
			return "redirect:/student/loginStudent";
		}	

		
		studentService.updateStudentPw(loginStudent.getStudentNo(), oldPw, newPw);
		
		return "redirect:/student/studentList";
	}
	//로그인 
	@GetMapping("/student/loginStudent")
	public String loginStudent(HttpSession session) {
		//이미 로그인 중이라면 redirect:/employee/empList
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		if(loginStudent != null) {
			return "redirect:/student/modifyStudentPw";
		}
		return "student/loginStudent";
	}
	@PostMapping("/student/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent = studentService.login(student);
		if(resultStudent == null) { //로그인 실패
			return "redirect:/student/loginStudent";
		} 
		session.setAttribute("loginStudent", resultStudent);
		return "redirect:/student/modifyStudentPw";
	}
	
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/student/loginStudent";
	}
	
	//삭제 
	@GetMapping("student/removeStudent")
	public String removeStudent(HttpSession session ,@RequestParam("studentNo") int studentNo) {


		studentService.removeStudent(studentNo);
		return "redirect:/student/studentList";
	}
	
	//입력
	@GetMapping("student/addStudent")
	public String addStudent(HttpSession session) {


		return "student/addStudent";
	}
	@PostMapping("/student/addStudent")
	public String addStudent(HttpSession session,Model model,Student student) {

		
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된Id");
			return "student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		// row == 1 이면 입력성공
		return "redirect:/student/studentList"; 
	}
	
	// 리스트 
	@GetMapping("/student/studentList")
	public String studentList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		//사원만 볼수있음
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "student/studentList";
		
	}
}
