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

@WebServlet(name = "UserBrowser", urlPatterns = "/")
public class UserBrowser extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String userBrowser = request.getHeader("User-Agent");

            PrintWriter printWriter = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            printWriter.println("Приветствую пользователя " + userBrowser);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

