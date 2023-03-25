package myServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Objects;

import static java.awt.SystemColor.text;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterValues("btnRegistration") != null) {
            resp.sendRedirect("registration");
        }
        if (req.getParameterValues("btnLogin") != null) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (checkForUser(login, password)) {
                File user = new File("C:\\Users\\настя\\source\\repos\\servletApp\\user.txt");
                try (PrintWriter out = new PrintWriter(user, StandardCharsets.UTF_8)) {
                    out.print(login);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Cookie cookie = new Cookie("idSession", req.getSession().getId());
                resp.addCookie(cookie);
                String path = "/servlet" + "?path=C:\\Users\\java\\" + login;
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("login");
            }
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    private boolean checkForUser(String login, String password) {
        User user = null;
        try {
            user = JDBCConnection.getUserByLogin(login);
            return JDBCConnection.authUser(user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        /*File user = new File("C:\\Users\\настя\\source\\repos\\servletApp\\users.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(user)))
        {
            String line;
            while((line = br.readLine()) != null) {
                if(line != null){
                    String[] data = line.split(";");
                    if(data[0].equals(login) && data[1].equals(password)){
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //return false;
    }
}
