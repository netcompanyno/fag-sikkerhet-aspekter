package com.netcompany.fagarbeid.aspektsikkerhet.filters;

import com.netcompany.fagarbeid.aspektsikkerhet.security.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper that maps the AccessDeniedException from the security aspect to a nice error.
 */
@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(AccessDeniedException e) {
        return Response.status(403).build();
    }
}
