package by.academy.it;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            String countText = "Servlet Cont:  \n" + Integer.toString(count);
            response.setContentType("image/jpeg");
            BufferedImage image = new BufferedImage(500, 200, BufferedImage.TYPE_USHORT_565_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setFont(new Font("Serif", Font.ITALIC, 48));
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.drawString(countText, 100, 100);

            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpeg", out);

            String valueCount = (new Date()) + ";" + " " + session.getId();
            servletLogger.setLogger(pathFile, valueCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

