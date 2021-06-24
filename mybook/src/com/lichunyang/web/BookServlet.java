package com.lichunyang.web;

import com.lichunyang.bean.Book;
import com.lichunyang.bean.Page;
import com.lichunyang.service.impl.BookServiceimpl;
import com.lichunyang.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookServiceimpl bsi = new BookServiceimpl();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("list方法调用！");
        List<Book> bookList = bsi.showBooks();
//        System.out.println(bookList);  //测试拿到数据。
        request.setAttribute("books", bookList);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamsToBean(request.getParameterMap(), new Book());
        System.out.println("检测是否将请求参数注入bean\n"+book);
        bsi.addBook(book);
        //用请求转发的话会出Bug，F5刷新浏览器， 浏览器会自动提交记录的最后一次请求，
        //浏览器会记录最后一次请求的全部信息
//        request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
        //使用重定向的话，记录的最后一次请求是action=list， 而转发记录的是action=add， 转发浏览器地址不变，还是add。
        int pageNo = Integer.parseInt(request.getParameter("totalPageNoForAdd"))+1;
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String delete_book_name = request.getParameter("delete_book_name");
        bsi.delteBookByName(request.getParameter("delete_book_name"));
        int pageNo = Integer.parseInt(request.getParameter("totalPageNoForAdd"));
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //点击修改后先到servlet程序处理，在转发到book.edit出，
        //应该通过id找唯一得书，这里就不修改了。
        List<Book> books = bsi.queryBookByName(request.getParameter("update_bookName"));
        request.setAttribute("update_books", books);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamsToBean(request.getParameterMap(), new Book());
        bsi.updateBookByName(book, book.getName());
        int pageNo = Integer.parseInt(request.getParameter("totalPageNoForAdd"));
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //考虑到默认的情况，没传数字，或者没传则 默认值
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);

        Page<Book> page = bsi.showPage(pageNo,pageSize );
        page.setUrl("manager/bookServlet?action=page");
        request.setAttribute("page", page);

        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

}