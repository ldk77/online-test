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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		//로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		return "employee/modifyEmpPw";
	}
	// pw수정 액션 
	@PostMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
								, @RequestParam(value="oldPw",required = true) String oldPw
								, @RequestParam(value="newPw",required = true) String newPw) {
		//로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		
		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
	}
	//로그인 폼
	@GetMapping("/loginEmp")
	public String loginEmp() {
		return "employee/loginEmp";
	}
	//로그인 액션
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, Employee employee) {
		Employee resultEmp = employeeService.login(employee);
	
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
	public String removeEmp(@RequestParam("empNo") int empNo) {

		employeeService.removeEmployee(empNo);
		return "redirect:/employee/empList";
	}
	
	//입력
	@GetMapping("employee/addEmp")
	public String addEmp() {
		return "employee/addEmp";
	}
	@PostMapping("/employee/addEmp")
	public String addEmp(Model model,Employee employee) {
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
	public String empList(Model model
			, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value="searchWord", defaultValue = "") String searchWord) {
			// int currentPage = request.getParameter("currentPage");		
		int lastPage = (int)Math.ceil((double)employeeService.lastPage(searchWord)/(double)rowPerPage);
		int startPage = (currentPage/rowPerPage)*10 +1;
		int endPage = (currentPage/rowPerPage)*10 + 10;
		if(endPage > lastPage) {
			endPage = lastPage;
		}
		if(lastPage<1) {
			lastPage= currentPage;
		}
		
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("lastPage", lastPage);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "employee/empList";
		
	}
}
