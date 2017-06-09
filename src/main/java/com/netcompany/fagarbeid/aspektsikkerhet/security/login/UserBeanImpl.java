package com.netcompany.fagarbeid.aspektsikkerhet.security.login;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class UserBeanImpl implements UserBean {
    private String user;

    public UserBeanImpl() {
        this.user = null;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public boolean isWebContext() {
        return true;
    }
}
