package by.academy.it;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "SessionCounterServlet", urlPatterns = "/")
public class SessionCounterServlet extends HttpServlet {

    private String pathFile;
    private int count = 0;
    ServletLogger servletLogger = new ServletLogger();


    @Override
    public void init() throws ServletException {
        pathFile = servletLogger.getLogger("ServletLog.txt", "files");
        count = servletLogger.getCounterValue(pathFile);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            count++;
            PrintWriter printWriter = response.getWriter();

            printWriter.println("Servlet Cont:  ");
            printWriter.println(count);
            String textCount = (new Date())+ ";" + " "+ session.getId();
            servletLogger.setLogger(pathFile, textCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

