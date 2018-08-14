package servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private ServletContext context;

    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        this.context.log("Session is " + (session == null? "null" : session.getId()));

        if (session == null) {
            request.getSession().setAttribute("error", "yes");
            response.sendRedirect("/index.jsp");
        } else {
            if (SessionTracker.getInstance().isSessionValid(session.getId())) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    public void destroy() {

    }
}
