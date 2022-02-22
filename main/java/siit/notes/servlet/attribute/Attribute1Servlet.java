package siit.notes.servlet.attribute;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/attribute1")
public class Attribute1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletContext context = getServletContext();
        PrintWriter out = resp.getWriter();
        context.setAttribute("attr1", "am setat valoarea din attr1");
        out.print("<a href=\"http://localhost:8080/attribute2\"> Go to attribute 2 </>");
        out.close();
    }
}
