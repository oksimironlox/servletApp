package myServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameterValues("btnLogin") != null){
            resp.sendRedirect("login");
        }

        if(req.getParameterValues("btnRegistration") != null){
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            File user = new File("C:\\Users\\настя\\source\\repos\\servletApp\\users.txt");
            try (FileWriter out = new FileWriter(user, StandardCharsets.UTF_8, true))
            {
                String txt = login + ";" + password + ";" + email +"\n";
                out.write(txt);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*User user = new User(login, email, password);
            try {
                if (!JDBCConnection.containsUser(user)) {
                    JDBCConnection.addUser(user);
                    resp.sendRedirect("/login");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            resp.sendRedirect("login");
        }

        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
}
