package com.netcompany.fagarbeid.aspektsikkerhet.rest;

import com.netcompany.fagarbeid.aspektsikkerhet.services.UserService;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Service
@Path("/user")
public class UserRestService {
    private final UserService userService;

    public UserRestService(final UserService userService) {
        Objects.requireNonNull(userService);
        this.userService = userService;
    }

    @GET
    @Path("{username}")
    public Response getUser(@PathParam("username") final String username) {
        return Response.ok(userService.getUser(username)).build();
    }

    @GET
    @Path("{username}/details")
    public Response getUserDetails(@PathParam("username") final String username) {
        return Response.ok(userService.getUserDetails(username)).build();
    }
}
