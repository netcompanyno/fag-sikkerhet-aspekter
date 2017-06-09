package com.netcompany.fagarbeid.aspektsikkerhet.security.predicates;


import com.netcompany.fagarbeid.aspektsikkerhet.security.login.UserBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class UserMatchesUsername implements SecurityPredicate {
    private final UserBean userBean;

    public UserMatchesUsername(UserBean userBean) {
        Objects.requireNonNull(userBean);
        this.userBean = userBean;
    }

    /**
     * Checks if the method parameter named username is equal to the currently logged in user.
     *
     * @param params The parameters the method is receiving.
     * @return True if the username matches the logged in user, false otherwise.
     */
    @Override
    public boolean checkAccess(Map<String, Object> params) {
        if (!params.containsKey("username")) {
            throw new IllegalArgumentException("Params does not contain username");
        }

        final String username = (String) params.get("username");

        return userBean.isLoggedIn() && userBean.getUser().equals(username);
    }
}
