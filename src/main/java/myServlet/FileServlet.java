package myServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/servlet"})
public class FileServlet extends HttpServlet {
    File file;

    String userName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        file = new File("C:\\Users\\java\\");
        req.setAttribute("name", file.getPath());
        req.setAttribute("files", file.listFiles());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getUserName();

        if(req.getParameterValues("btnExit") != null){
            resp.sendRedirect("login");
        }

        if (req.getParameterValues("btnBack") != null){
            File newfile = file.getParentFile();
            File parent = new File("C:\\Users\\java");
            if(!newfile.getPath().equals(parent.getPath())){
                file = file.getParentFile();
            }
        }
        else if (req.getParameterValues("btn") != null){
            file = new File(req.getParameterValues("btn")[0]);
        }
        else{
            file = new File("C:\\Users\\java\\" + userName);
        }
        if (file == null){
            file = new File("C:\\");
        }
        if(!file.isDirectory()){
            file = file.getParentFile();
        }
        req.setAttribute("name", file.getPath());
        req.setAttribute("files", file.listFiles());

        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    private void getUserName(){

            File user = new File("C:\\Users\\настя\\source\\repos\\servletApp\\user.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(user)))
            {
                String line;
                while((line = br.readLine()) != null) {
                    if(line != null){
                        userName = line;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}