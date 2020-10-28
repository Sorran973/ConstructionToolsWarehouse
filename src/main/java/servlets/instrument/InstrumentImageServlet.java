package servlets.instrument;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/instrumentImage")
public class InstrumentImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int instId = Integer.parseInt(req.getParameter("instId"));
            byte[] image = Database.getImageInstrumentById(instId);
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(image);
        }
        catch(Exception ex) {
//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
