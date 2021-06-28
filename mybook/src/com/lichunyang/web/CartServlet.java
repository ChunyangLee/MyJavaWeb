package com.lichunyang.web;

import com.google.gson.Gson;
import com.lichunyang.bean.Cart;
import com.lichunyang.bean.CartItem;
import com.lichunyang.service.impl.BookServiceimpl;
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
import java.util.HashMap;
import java.util.Map;

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
        //add on 2021.6.26 09:08 ,
        //要检验该id的商品是否存在于数据库中，防止 跳过浏览器直接向服务器发请求
//      BookServiceimpl bsi = new BookServiceimpl();     //根据id查找，有的话再添加

        cart.addCartItem(item);     //count的设置逻辑不在此处，
        //加入成功后，库存-1，xx  生成订单时操作

        //第三步： 放入session域中， 请求转发到显示购物车
        session.setAttribute("cart", cart);
        session.setAttribute("lastItem", item);

        String pageId = request.getParameter("pageId");
//        System.out.println("当前访问服务器的浏览器地址: "+ request.getHeader("Referer"));
//        System.out.println("addItem servlet 获取到了pageId: "+pageId);
        response.sendRedirect(request.getHeader("Referer"));


//        request.getRequestDispatcher("/index.jsp?pageNo="+pageId);
    }
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = null;
        if (session.getAttribute("cart") == null) {
            cart = new Cart();

        } else {
            cart = (Cart) session.getAttribute("cart");
        }

        CartItem item = WebUtils.copyParamsToBean(request.getParameterMap(), new CartItem());

        cart.addCartItem(item);     //count的设置逻辑不在此处，

        session.setAttribute("cart", cart);
        session.setAttribute("lastItem", item);      //用ajax就不需要放入session里了,
        // 错的。 必须放里面，而且在index界面也要写这个，否则点页码刷新index时，没走这个ajaxAddItem，span里面就没有值了
        //span里的值始终为session里的，只是点击购物车时，响应更信了一下，

        String pageId = request.getParameter("pageId");

        // added on 2021.6.27
        //用ajax做， 前面都一样，只是重定向不用了， 将页面需要的东西，打包成json，write到页面，在js代码中局部改变页面信息
        Map<String,Object> map = new HashMap<>();
        map.put("cartTotalCount",cart.getTotalCount());
        map.put("lastItemName",item.getName());
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(map));

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

        //可根据id查找到图书商品，得到库存等信息来做检验
        cart.updateItemCountById(id,count);

//        response.sendRedirect("/mybook/pages/cart/cart.jsp");
        //从哪来回哪去，也可以使用getHeader
        response.sendRedirect(request.getHeader("Referer"));

    }



}