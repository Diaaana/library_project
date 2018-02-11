package by.radomskaya.project.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.radomskaya.project.constant.JspPage.START_PAGE;

@WebFilter( urlPatterns = { "/jsp/user/main.jsp" },initParams = { @WebInitParam(name = "START_PATH", value = "/jsp/start.jsp") })
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String type = (String) session.getAttribute("role");
        if (type == null) {
            session.setAttribute("role", "guest");
            RequestDispatcher dispatcher = request.getRequestDispatcher(START_PAGE);
            dispatcher.forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
