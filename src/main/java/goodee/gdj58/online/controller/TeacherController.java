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
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;


@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session) {
		//로그인 후 호출 가능
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		return "teacher/modifyTeacherPw";
	}
	// pw수정 액션 
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher == null) {
			return "redirect:/teacher/loginTeacher";
		}
		
		teacherService.updateTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		
		return "redirect:/teacher/teacherList";
	}
	//로그인 
	@GetMapping("/teacher/loginTeacher")
	public String loginTeacher(HttpSession session) {
		//이미 로그인 중이라면 redirect:/employee/empList
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		if(loginTeacher != null) {
			return "redirect:/teacher/teacherList";
		}
		return "teacher/loginTeacher";
	}
	@PostMapping("/teacher/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher = teacherService.login(teacher);
		if(resultTeacher == null) { //로그인 실패
			return "redirect:/teacher/loginTeacher";
		} 
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/teacher/modifyTeacherPw";
	}
	
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/teacher/loginTeacher";
	}
	
	//삭제 
	@GetMapping("teacher/removeTeacher")
	public String removeTeacher(HttpSession session ,@RequestParam("teacherNo") int teacherNo) {


		teacherService.removeTeacher(teacherNo);
		return "redirect:/teacher/teacherList";
	}
	
	//입력
	@GetMapping("teacher/addTeacher")
	public String addTeacher(HttpSession session) {


		return "teacher/addTeacher";
	}
	@PostMapping("/teacher/addTeacher")
	public String addTeacher(HttpSession session,Model model,Teacher teacher) {

		
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된Id");
			return "teacher/addTeacher";
		}
		
		int row = teacherService.addTeacher(teacher);
		// row == 1 이면 입력성공
		return "redirect:/teacher/teacherList"; 
	}
	
	// 리스트 
	@GetMapping("/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		//사원만 볼수있음
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
			
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "teacher/teacherList";
		
	}
}
