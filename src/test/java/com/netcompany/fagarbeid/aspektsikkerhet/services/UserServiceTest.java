package com.netcompany.fagarbeid.aspektsikkerhet.services;

import com.netcompany.fagarbeid.aspektsikkerhet.SecuredTestUtil;
import com.netcompany.fagarbeid.aspektsikkerhet.dao.UserDao;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.FirstParamIsAleksi;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.UserIsAdmin;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.UserMatchesUsername;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;

    @Test
    public void getUser_shouldCallDao() throws Exception {
        when(userDao.getUser("testname")).thenReturn("testvalue");

        final String returnvalue = userService.getUser("testname");

        verify(userDao).getUser("testname");
        assertThat(returnvalue).isEqualTo("testvalue");
    }

    @Test
    public void getUser_shouldBeSecured() throws Exception {
        SecuredTestUtil.checkSecuredOnMethod(userService,
                "getUser",
                UserIsAdmin.class,
                FirstParamIsAleksi.class,
                UserMatchesUsername.class);
    }
}