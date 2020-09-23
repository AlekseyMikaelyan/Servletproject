package org.example.servletproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "servlet-example", urlPatterns = "/example")
public class ServletExample extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ServletExample.class);

    private final Map<String, String> userInfo = new ConcurrentHashMap<>();

    @Override
    public void init() {
        log.info("Servlet example initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");

        userInfo.put(req.getRemoteAddr(), req.getHeader("User-Agent"));

        for (Map.Entry<String, String> info : userInfo.entrySet()) {
            if(!info.getKey().equals(req.getRemoteAddr())) {
                responseBody.println("<p><b>" + info.getKey() + " :: " + info.getValue() + "</b></p>");
            }
        }
    }

    @Override
    public void destroy() {
        log.info("Servlet example destroyed");
    }
}
