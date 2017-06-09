package com.netcompany.fagarbeid.aspektsikkerhet.services;

import com.netcompany.fagarbeid.aspektsikkerhet.dao.UserDao;
import com.netcompany.fagarbeid.aspektsikkerhet.security.Secured;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.FirstParamIsAleksi;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.UserIsAdmin;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.UserMatchesUsername;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        Objects.requireNonNull(userDao);
        this.userDao = userDao;
    }

    @Secured({UserIsAdmin.class, FirstParamIsAleksi.class, UserMatchesUsername.class})
    public String getUser(final String username) {
        return userDao.getUser(username);
    }

    @Secured({UserIsAdmin.class})
    public String getUserDetails(final String username) {
        // Should have more details here...
        return userDao.getUser(username);
    }
}
