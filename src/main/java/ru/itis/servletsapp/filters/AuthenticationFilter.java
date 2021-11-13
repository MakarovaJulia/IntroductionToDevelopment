package ru.itis.servletsapp.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().startsWith("/css")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getRequestURI().startsWith("/resources")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getRequestURI().startsWith("/files")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getRequestURI().startsWith("/main")) {
            filterChain.doFilter(request, response);
            return;
        }

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        boolean isAuthenticated = false;
        // существует ли сессия вообще?
        boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        boolean isRequestOnAuthPage = request.getRequestURI().equals("/sign-in") ||
                request.getRequestURI().equals("/sign-up");

        boolean isRequestOnProfilePage = request.getRequestURI().equals("/profile");

        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user") != null;
        }

        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage){
            // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnAuthPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect("/profile");

        } else if(!isAuthenticated && isRequestOnProfilePage){
            response.sendRedirect("/sign-in");

        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы

            response.sendRedirect("/main");
        }
    }

    @Override
    public void destroy() {}
}