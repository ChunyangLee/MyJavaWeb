package com.lichunyang.filter;

import com.lichunyang.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(req, resp);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.RollbackAndClose();
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
