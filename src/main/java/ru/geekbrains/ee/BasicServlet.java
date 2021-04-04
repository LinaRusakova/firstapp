package ru.geekbrains.ee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class BasicServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    }

    private transient ServletConfig config;

    // Метод вызывается контейнером после того как был создан класс сервлета
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Сохраняем полученную от сервера конфигурацию
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info("New request");

        // получаем объект типа BufferedWriter и пишем в него ответ на пришедший запрос

        Product[] prod = new Product[10];

        for (int i = 0; i < prod.length; i++) {
            prod[i] = new Product(i, "Наш супер Продукт №" + i,  (i*Math.random()) * 10);
        }


        res.setContentType("text/html");
        res.setCharacterEncoding("Utf-8");
        PrintWriter out = res.getWriter();
        out.println("<html><head>");
        out.println("<title>List of 10 our good products</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>List of 10 our good products!</h1>");
        out.println("<table border=1 cellpadding=5 style='border-collapse: collapse; border: 1px solid black;'><tr><th>Product ID</th><th>Product Name</th><th>Cost</th></tr>");
        for (Product pr : prod) {
            out.printf("<tr><td>%d</td><td>%s</td><td>%8.2f</td></tr>", pr.getId(), pr.getTitle(), pr.getCost());
        }
        out.println("</table></body></html>");
    }

    @Override
    public String getServletInfo() {
        return "BasicServlet";
    }

    // При завершении работы веб приложения, контейнер вызывает этот метод для всех сервлетов из этого приложения
    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}

