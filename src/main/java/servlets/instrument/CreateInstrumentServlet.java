package servlets.instrument;

import objectsDB.Database;
import objectsDB.Instrument;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/createInstrument")
@MultipartConfig
public class CreateInstrumentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createInstrument.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            String instType = request.getParameter("instType");
            String instName = request.getParameter("instName");
            int instPrice = Integer.parseInt(request.getParameter("instPrice"));
            String instDescription = request.getParameter("instDescription");
            Part part = request.getPart("image");
            InputStream inputStream = part.getInputStream();
            Instrument instrument = new Instrument(0, instType, instName, instPrice, 0, 0, instDescription);
            Database.insertInstrument(instrument, inputStream);
            response.sendRedirect(request.getContextPath() + "/createInstrument.jsp");

        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/createInstrument.jsp").forward(request, response);
        }
    }
}
