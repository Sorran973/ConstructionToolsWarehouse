package servlets.supply;

import objectsDB.Database;
import objectsDB.Instrument;
import objectsDB.Supply;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/editSupply")
public class EditSupplyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int supId = Integer.parseInt(req.getParameter("supId"));
            Supply supply = Database.getSupplyById(supId);
            if(supply!=null) {
                req.setAttribute("supply", supply);
                req.setAttribute("objectList", Database.selectObjectList());
                req.setAttribute("instrumentList", Database.selectInstrumentList());
                getServletContext().getRequestDispatcher("/editSupply.jsp").forward(req, resp);
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
            int supId = Integer.parseInt(req.getParameter("supId"));
            String supObjId = req.getParameter("supObjId");
            Date supDate = Date.valueOf(req.getParameter("supDate"));
            String[] instIdList = req.getParameterValues("instIdList");
            Supply supply = new Supply(supId, 1, supDate);
            Database.updateSupply(supply, supObjId);
            resp.sendRedirect(req.getContextPath() + "/supplies");
        }
        catch(Exception ex) {

//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
