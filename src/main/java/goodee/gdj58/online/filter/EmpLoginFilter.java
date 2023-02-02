package goodee.gdj58.online.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log();이런 개념
@WebFilter("/employee/*")
public class EmpLoginFilter extends HttpFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("\u001B[31m" + "EmpLoginFilter 실행");
		
	if(request instanceof HttpServletRequest) {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession(); 
		if(session.getAttribute("loginEmp") == null) {
			((HttpServletResponse) response).sendRedirect(req.getContextPath()+"/loginEmp");
			return;
		}
	} else {
		log.debug("\u001B[31m" + "웹브라우저 요청만 허용합니다");
		return;
	}
	
		//controller 전
		chain.doFilter(request, response);
		//controller 후 
	}


}
