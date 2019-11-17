package com.mzc.controller;

import com.mzc.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 登录认证拦截器
 */
public class LoginHandler implements HandlerInterceptor {
    /**
     * 是controller的前置方法，当方法返回false整个请求就结束了。
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String url=httpServletRequest.getRequestURI();
        //System.out.println("RequestURI:"+url);
        if(url.indexOf("loginValidate")>0){
            return true;
        }else if(url.indexOf("registerValidate")>0){
            return true;
        }
        if(user==null) {
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().println("<script>");
            httpServletResponse.getWriter().println("alert('请先登录!')");
            httpServletResponse.getWriter().println("window.location.href='login.jsp'");
            httpServletResponse.getWriter().println("</script>");
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
            //httpServletResponse.sendRedirect("/Demo/login.jsp");
            return false;
        }else
            return true;
    }
  /**
   *就是在当前请求进行处理之后，也就是Controller方法调用之后执行，
   * 但是它会在DispatcherServlet进行视图返回渲染之前被调用。
   */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * 可用于清理资源,统一异常处理，统一日志处理。
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
