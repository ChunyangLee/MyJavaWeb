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
import java.util.List;

public class SearchBookServlet extends BaseServlet {
    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServiceimpl bsi = new BookServiceimpl();
        Double min= WebUtils.parseDouble(request.getParameter("min"), 10.0);
        Double max= WebUtils.parseDouble(request.getParameter("max"), 50.0);

        List<Book> bookList = bsi.queryBookByPrice(min, max);
        request.setAttribute("bookList", bookList);

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}
