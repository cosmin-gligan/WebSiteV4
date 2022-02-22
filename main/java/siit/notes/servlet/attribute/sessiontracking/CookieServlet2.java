package siit.notes.servlet.attribute.sessiontracking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie_servlet2")
public class CookieServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
//            if(cookie.getName().equals("user")){
//                ....
//                break;
//            }
            out.print("<h3>" + cookie.getName() + " " + cookie.getValue() + "</h3>");
        }

    }
}
