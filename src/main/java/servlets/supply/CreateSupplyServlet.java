package servlets.supply;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/createSupply")
public class CreateSupplyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("objectList", Database.selectObjectList());
        request.setAttribute("instrumentList", Database.selectInstrumentList());
        getServletContext().getRequestDispatcher("/createSupply.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            String supObjId = request.getParameter("supObjId");
            Date supDate = Date.valueOf(request.getParameter("supDate"));
            String[] instIdList = request.getParameterValues("instIdList");
            if (!supObjId.equals("")) {
                Database.insertSupply(supObjId, supDate, instIdList);
                response.sendRedirect(request.getContextPath() + "/createSupply.jsp");
            }
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/createSupply.jsp").forward(request, response);
        }
    }
}
