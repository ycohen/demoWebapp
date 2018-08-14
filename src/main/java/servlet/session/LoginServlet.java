package servlet.session;

import servlet.filter.SessionTracker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (("Test1".equals(username) && "test1@mytest.com".equals(password)) ||
                ("Test2".equals(username) && "test2@mytest.com".equals(password))) {
            HttpSession session = request.getSession(true);
            SessionTracker.getInstance().addSession(session.getId());

            session.setMaxInactiveInterval(5*60);
            response.sendRedirect("/orders.jsp");
        } else {
            request.getSession().setAttribute("error", "yes");
            response.sendRedirect("/index.jsp");
        }
    }
}
