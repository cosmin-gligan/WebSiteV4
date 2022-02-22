package siit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String password = req.getParameter("password");
        if(user.equals(password)){
            //logare cu succes
            req.getSession(true).setAttribute("logged_user", user);
            resp.sendRedirect("/customers");
        }else {
            //failed
            String errorMessage = "User and password do not match!";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("WEB-INF/login.jsp")
                    .forward(req, resp);
        }
    }
}
