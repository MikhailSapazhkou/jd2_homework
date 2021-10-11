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

@WebServlet(name = "UserForm", urlPatterns = "/form")
public class UserForm extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String fio = request.getParameter("fio");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            PrintWriter printWriter = response.getWriter();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            if (fio.equals("")){
                printWriter.println("Ошибка! <br/>" +
                        "Фамилия Имя Отчество должны быть заполнены обязательно");
            }else if (email.equals("")  & phone.equals("")){
                printWriter.println("Ошибка! <br/>" +
                        "Должен быть заполнен e-mail или номер телефона");
            } else {
                printWriter.println("СПАСИБО! <br/><br/>" +
                        "Вы зарегистрированы как <br/><br/>");
                printWriter.println(fio + "<br/><br/>");
                printWriter.println("E-mail :"+email + "<br/><br/>");
                printWriter.println("Телефон :"+phone);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

