package siit.notes.servlet.attribute.sessiontracking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie_servlet1")
public class CookieServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Cookie cookie = new Cookie("user", "Lucian");
        Cookie cookie2 = new Cookie("user2", "Lucian");
        Cookie cookie3 = new Cookie("user3", "Lucian");
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);
        resp.getWriter().print("<h1>Am adaugat cookie</h1>");

    }
}
