package servlets.object;

import objectsDB.Database;
import objectsDB.Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editObject")
public class EditObjectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            int objId = Integer.parseInt(req.getParameter("objId"));
            Object object = Database.getObjectById(objId);
            if(object!=null) {
                req.setAttribute("object", object);
                getServletContext().getRequestDispatcher("/editObject.jsp").forward(req, resp);
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
            int objId = Integer.parseInt(req.getParameter("objId"));
            String objAddress = req.getParameter("objAddress");
            Object object = new Object(objId, objAddress);
            Database.updateObject(object);
            resp.sendRedirect(req.getContextPath() + "/objects");
        }
        catch(Exception ex) {

//            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
