package servlets.supply;

import objectsDB.Database;
import objectsDB.Supply;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supplyData")
public class SupplyDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int supId = Integer.parseInt(req.getParameter("supId"));
                req.setAttribute("instrumentList", Database.selectInstrumentListBySupply(supId));
                getServletContext().getRequestDispatcher("/supplyData.jsp").forward(req, resp);
        }
        catch(Exception ex) {
//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
