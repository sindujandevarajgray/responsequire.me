package com.exon.test.app.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Logger;

import org.json.JSONObject;


@WebServlet(name = "Servlet_getter", value = "/req")
public class Servlet_getter extends HttpServlet {
    Logger log = Logger.getLogger("Request Record");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request,response);
    }
    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");

        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            out.write("["+paramName);
            out.write(" <-- paramName] ");

            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write(" [" + paramValue);
                out.write(" <-- paramValue] \n");
                map.put(paramName,paramValue);
            }

        }
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        log.info(" Response-"+jsonObject.toString());

        out.close();

    }
}
