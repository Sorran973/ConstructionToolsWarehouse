package servlets;

import objectsDB.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/databaseConfig")
public class DatabaseConfigServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        Database.setDatabase(req.getParameter("driver"),
                req.getParameter("url"),
                req.getParameter("user"),
                req.getParameter("password"));

//        resp.sendRedirect("/main");
//        req.setAttribute("objectList", Database.selectObjectList());
        getServletContext().getRequestDispatcher("/main").forward(req, resp);


//        HttpSession session = req.getSession();
//        session.setAttribute("database", dataBase);
//        session.setAttribute("objectList", dataBase.selectObjectList());
//
//        req.setAttribute("obj", objects.get(0));
//        getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
//                resp.sendRedirect("/main.jsp");

//        try{
//            Class.forName(dataBase.getDriver()).getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(dataBase.getUrl(), dataBase.getUser(), dataBase.getPassword())){
//                writer.println("<h4>Connection to Database SUCCESFULL!<h4>");
//
//                Statement statement = conn.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM objects");
//                ArrayList<Object> objects = new ArrayList<Object>();
//                while(resultSet.next()){
//                    int id = resultSet.getInt(1);
//                    String address = resultSet.getString(2);
//                    Object object = new Object(id, address);
//                    objects.add(object);
//                }
//
//                HttpSession session = req.getSession();
//                session.setAttribute("database", dataBase);
//                session.setAttribute("objectList", objects);
//
//                req.setAttribute("obj", objects.get(0));
//                getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
////                resp.sendRedirect("/main.jsp");
//            }
//        }
//        catch(Exception ex){
//            writer.println("<h4>Connection failed...<h4>");
//            writer.println(ex);
//        }
//        finally {
//            writer.close();
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        }
}
