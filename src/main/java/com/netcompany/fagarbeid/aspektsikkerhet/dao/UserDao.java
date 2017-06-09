package com.netcompany.fagarbeid.aspektsikkerhet.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public String getUser(final String username) {
        return "Test";
    }
}
