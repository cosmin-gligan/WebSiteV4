package siit.notes.servlet.firstservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/response_status")
public class StatusCodesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final int responseCode;
        if (req.getParameterMap().containsKey("code")) {
            final String responseCondeString = req.getParameterMap().get("code")[0];
            responseCode = Integer.parseInt(responseCondeString);
        } else {
            responseCode = HttpServletResponse.SC_OK;
        }
        resp.setStatus(responseCode);
    }
}
