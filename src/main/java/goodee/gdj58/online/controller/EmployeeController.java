package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;

@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		//로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		return "employee/modifyEmpPw";
	}
	// pw수정 액션 
	@PostMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
	}
	//로그인 
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		//이미 로그인 중이라면 redirect:/employee/empList
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		return "employee/loginEmp";
	}
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee employee) {
		Employee resultEmp = employeeService.login(employee);
		if(resultEmp == null) { //로그인 실패
			return "redirect:/employee/loginEmp";
		} 
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
	
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
	
	//삭제 
	@GetMapping("employee/removeEmp")
	public String removeEmp(HttpSession session ,@RequestParam("empNo") int empNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}

		employeeService.removeEmployee(empNo);
		return "redirect:/employee/empList";
	}
	
	//입력
	@GetMapping("employee/addEmp")
	public String addEmp(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}

		return "employee/addEmp";
	}
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session,Model model,Employee employee) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		String idCheck = idService.getIdCheck(employee.getEmpId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된Id");
			return "employee/addEmp";
		}
		
		int row = employeeService.addEmployee(employee);
		// row == 1 이면 입력성공
		return "redirect:/employee/empList"; 
	}
	
	// 리스트 
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage) {
			// int currentPage = request.getParameter("currentPage");
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "employee/empList";
		
	}
}
