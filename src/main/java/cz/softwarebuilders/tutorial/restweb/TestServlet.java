package cz.softwarebuilders.tutorial.restweb;

import cz.softwarebuilders.tutorial.restweb.spring.beans.TestBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestBean bean = WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBean(TestBean.class);
        PrintWriter writer = response.getWriter();
        writer.append(bean.whowAmI());
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
