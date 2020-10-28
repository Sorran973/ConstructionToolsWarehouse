package servlets;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/procedure")
public class ProcedureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<ArrayList> res = Database.procedure();
        req.setAttribute("instType", res.get(0));
        req.setAttribute("instAmount", res.get(1));
        getServletContext().getRequestDispatcher("/procedure.jsp").forward(req, resp);
    }
}
