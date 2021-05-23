package cz.fi.muni.pa165.hockeymanager.mvc;

import cz.fi.muni.pa165.hockeymanager.dto.UserDto;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebFilter;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/", "/user/*", "/players/*", "/team/*", "/match/*"})
public class LoggedInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserDto authUser = (UserDto)session.getAttribute("authenticatedUser");

        if (authUser == null) {
            String redirectURL = request.getContextPath() + "/auth/login";
            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            response.setHeader("Location", redirectURL);
        }

        filterChain.doFilter(request, response);
    }
}
