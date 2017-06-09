package com.netcompany.fagarbeid.aspektsikkerhet.security.login;

public interface UserBean {
    default void setUser(final String user) {
        return;
    }

    default String getUser() {
        return null;
    }

    default boolean isLoggedIn() {
        return false;
    }

    default boolean isWebContext() {
        return false;
    }
}
