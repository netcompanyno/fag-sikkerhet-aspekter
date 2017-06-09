package com.netcompany.fagarbeid.aspektsikkerhet.filters;

import com.netcompany.fagarbeid.aspektsikkerhet.security.login.UserBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

/**
 * Very simple login filter that stores the value in the cookie "user" as the logged in users username.
 * <p>
 * In a real project we would use something like Spring Security to handle the authentication of users.
 */
@Provider
public class LoginFilter implements ContainerRequestFilter {
    @Autowired
    private UserBean userBean;

    @Override
    public void filter(final ContainerRequestContext containerRequestContext) throws IOException {
        final Map<String, Cookie> cookies = containerRequestContext.getCookies();

        if (cookies == null) {
            return;
        }

        final Cookie userCookie = cookies.get("user");
        if (userCookie == null) {
            return;
        }

        userBean.setUser(userCookie.getValue());
    }
}
