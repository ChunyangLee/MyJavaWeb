package web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("test");
//        //因为是表单数据多段拼接，以流的方式存储的，因此直接getParameter得不到数据
//        ServletInputStream is = request.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ( (len=is.read(bytes))!=-1 ){
//            bos.write(bytes,0,len);
//        }
//        System.out.println(bos.toString());

        //使用第三方jar包，解析
        //判断是不是多段格式的，
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        //1。 是多段格式才处理
        if(ServletFileUpload.isMultipartContent(request)){
            List<FileItem> list=null;
            //2。 获取上传到的数据
            try {
                 list = fileUpload.parseRequest(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //3。 遍历上传到的每项
            for(FileItem item: list){
                //4。 判断是否是普通的表单项，否则是上传的文件类型
                if(item.isFormField()){
                    System.out.println(item.getFieldName());
                    System.out.println(item.getString("utf-8"));
                }else {
                    System.out.println(item.getFieldName());
                    System.out.println(item.getName());
                    try {
                        item.write(new File("/Users/lichunyang/Documents/1.jpg"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
