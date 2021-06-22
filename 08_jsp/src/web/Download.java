package web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class Download extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取要下载的文件名
        String downloadFileName = "1.jpg";
        //2。 读取要下载的文件内容
        ServletContext servletContext = getServletContext();
        InputStream is = servletContext.getResourceAsStream("/" + downloadFileName);
        //4.  通过响应头告诉客户端数据类型
        String mimeType = servletContext.getMimeType("/" + downloadFileName);
        response.setContentType(mimeType);
        System.out.println(mimeType);
//        response.setHeader("Content-Disposition", "attachment;filename="+downloadFileName);
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("豪车.jpg", "utf-8"));
        //3.  传数据到客户端
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);

        //5.  通过响应头告诉客户端（浏览器），收到的数据是下载用，否则直接显示到浏览器上了
    }
}
