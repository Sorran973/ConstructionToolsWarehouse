package servlets.instrument;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/instruments")
public class InstrumentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("instrumentList", Database.selectInstrumentList());
        getServletContext().getRequestDispatcher("/instruments.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("instrumentList", Database.selectInstrumentList());
        getServletContext().getRequestDispatcher("/instruments.jsp").forward(req, resp);
    }
}
