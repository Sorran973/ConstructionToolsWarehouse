package servlets.instrument;

import objectsDB.Database;
import objectsDB.Instrument;
import objectsDB.Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/editInstrument")
@MultipartConfig
public class EditInstrumentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int instId = Integer.parseInt(req.getParameter("instId"));
            Instrument instrument = Database.getInstrumentById(instId);
            if(instrument!=null) {
                req.setAttribute("instrument", instrument);
                getServletContext().getRequestDispatcher("/editInstrument.jsp").forward(req, resp);
            }
            else {
//                getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
            }
        }
        catch(Exception ex) {
//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int instId = Integer.parseInt(req.getParameter("instId"));
            String instType = req.getParameter("instType");
            String instName = req.getParameter("instName");
            int instPrice = Integer.parseInt(req.getParameter("instPrice"));
            int instCondition = Integer.parseInt(req.getParameter("instCondition"));
            int instLocation = Integer.parseInt(req.getParameter("instLocation"));
            String instDescription = req.getParameter("instDescription");
            Part part = req.getPart("image");
            if (part.getSize() == 0){
                Instrument instrument = new Instrument(instId, instType, instName, instPrice, instCondition, instLocation, instDescription);
                Database.updateInstrumentWithoutInputStream(instrument);
                resp.sendRedirect(req.getContextPath() + "/instruments");
            }
            InputStream inputStream = part.getInputStream();
            Instrument instrument = new Instrument(instId, instType, instName, instPrice, instCondition, instLocation, instDescription);
            Database.updateInstrument(instrument, inputStream);
            resp.sendRedirect(req.getContextPath() + "/instruments");
        }
        catch(Exception ex) {

//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
