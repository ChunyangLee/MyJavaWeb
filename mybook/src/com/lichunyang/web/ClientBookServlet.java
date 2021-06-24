package com.lichunyang.web;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;
import com.lichunyang.service.impl.BookServiceimpl;
import com.lichunyang.utils.WebUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *    用于前台访问的，
 */
public class ClientBookServlet extends BaseServlet {

    BookServiceimpl bsi = new BookServiceimpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //考虑到默认的情况，没传数字，或者没传则 默认值
        System.out.println("经过clicentBookServlet！");
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
//        System.out.println("min为: "+request.getParameter("min"));
        Double min= WebUtils.parseDouble(request.getParameter("min"), 10.0);
//        System.out.println("Double parse之后的min为"+min);
        Double max= WebUtils.parseDouble(request.getParameter("max"), 50.0);

        Page<Book> page = bsi.showPage(pageNo,pageSize,min,max );
        page.setUrl("bookServlet?min="+min+"&max="+max+"&action=page");

        request.setAttribute("page", page);
        request.setAttribute("min", min);
        request.setAttribute("max", max);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}
