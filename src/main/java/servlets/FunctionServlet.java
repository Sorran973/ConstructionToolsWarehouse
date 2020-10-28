package servlets;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/function")
public class FunctionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("instrumentList", Database.selectInstrumentList());
        getServletContext().getRequestDispatcher("/functionPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String instrument = request.getParameter("instrument");
        request.setAttribute("instType", instrument);
        request.setAttribute("answer", Database.function(instrument));
        getServletContext().getRequestDispatcher("/functionGet.jsp").forward(request, response);
    }
}
