package servlets.object;

import objectsDB.Database;
import objectsDB.Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createObject")
public class CreateObjectServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/createObject.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
            String address = request.getParameter("address");
            if (!address.equals("")) {
                Database.insertObject(address);
                response.sendRedirect(request.getContextPath() + "/createObject.jsp");
            }
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/createObject.jsp").forward(request, response);
        }
    }
}