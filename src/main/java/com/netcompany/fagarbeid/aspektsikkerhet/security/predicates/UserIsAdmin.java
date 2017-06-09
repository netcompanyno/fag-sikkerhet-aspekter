package com.netcompany.fagarbeid.aspektsikkerhet.security.predicates;

import com.netcompany.fagarbeid.aspektsikkerhet.security.login.UserBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class UserIsAdmin implements SecurityPredicate {
    private final UserBean userBean;

    public UserIsAdmin(UserBean userBean) {
        Objects.requireNonNull(userBean);
        this.userBean = userBean;
    }

    /**
     * Checks if the user is an admin user.
     *
     * @param params The parameters the method is receiving.
     * @return True if the user is an admin, false otherwise.
     */
    @Override
    public boolean checkAccess(Map<String, Object> params) {
        if (!userBean.isLoggedIn()) {
            return false;
        }

        return userBean.getUser().equals("ADMIN");
    }
}
