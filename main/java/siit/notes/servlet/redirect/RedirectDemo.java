package siit.notes.servlet.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/redirect")
public class RedirectDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setStatus(301);
////        resp.setStatus(200); nu o sa mearga redirec pentru ca trebuie musai status 3xx
//        resp.setHeader("Location", "https://www.google.ro");

        resp.sendRedirect("http://www.google.com");
    }
}
