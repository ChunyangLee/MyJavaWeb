package com.lichunyang.web;

import com.lichunyang.bean.Cart;
import com.lichunyang.bean.CartItem;
import com.lichunyang.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class CartServlet extends BaseServlet {
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //点击主页中的添加购物车，将商品item添加到Cart中的CartItem数组中，
        //第一步： 如果没有Cart对象，就造一个，
        HttpSession session = request.getSession();
        Cart cart = null;
        if (session.getAttribute("cart") == null) {
//            System.out.println("新创建购物车对象，");
            cart = new Cart();

        } else {
//            System.out.println("已经有购物车了，取出保存在session中的购物车！");
            cart = (Cart) session.getAttribute("cart");
        }

        //第二步：加入购物项 ，  获取请求参数，封装进CartItem中，
        CartItem item = WebUtils.copyParamsToBean(request.getParameterMap(), new CartItem());
        cart.addCartItem(item);     //count的设置逻辑不在此处，

        //第三步： 放入session域中， 请求转发到显示购物车
        session.setAttribute("cart", cart);
//        request.getRequestDispatcher("/pages/cart/cart.jsp").forward(request, response);

//        response.sendRedirect("/mybook/pages/cart/cart.jsp");
        response.sendRedirect("/mybook/index.jsp");

//        String pageId = request.getParameter("pageId");
//        System.out.println("addItem servlet 获取到了pageId");
//        request.getRequestDispatcher("/index.jsp?pageNo="+pageId);
    }

    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clear();

//        request.getSession().setAttribute("cart", cart); // 是引用类型，cart指向session域中那个
        response.sendRedirect("/mybook/pages/cart/cart.jsp");

    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //有session，有购物车，有items才会在cart.jsp中显示删除按钮
//        System.out.println("deleteItem");
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        String itemId = request.getParameter("itemId");

        cart.deleteItemByKey(Integer.parseInt(itemId));

        response.sendRedirect("/mybook/pages/cart/cart.jsp");
    }

    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("updateItem");
        Cart cart = (Cart)request.getSession().getAttribute("cart");

        Integer count = Integer.parseInt(request.getParameter("count"));
        Integer id = Integer.parseInt(request.getParameter("id"));

        cart.updateItemCountById(id,count);

        response.sendRedirect("/mybook/pages/cart/cart.jsp");

    }



}