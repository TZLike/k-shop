package com.huatech.shop.base.controller;

import com.huatech.shop.common.result.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author like
 * @Date 2019-08-16 10:37
 * @Version 1.0
 **/
public class BaseController {


    @Autowired
    private HttpServletRequest request;

    public PageQuery getPageQuery() {
        PageQuery pageQuery = new PageQuery();

        int page = 0;
        int size = 10;
        if (!org.springframework.util.StringUtils.isEmpty(request.getParameter("pageNumber"))) {
            page = Integer.parseInt(request.getParameter("pageNumber")) - 1;
            size = Integer.parseInt(request.getParameter("pageSize"));
        }
        pageQuery.setPageQuery_currPage(page);
        pageQuery.setPageQuery_pageSize(size);
        return pageQuery;
    }

    /**
     * 带参重定向
     *
     * @param path
     * @return
     */
    protected String redirect(String path) {
        return "redirect:" + path;
    }


}
