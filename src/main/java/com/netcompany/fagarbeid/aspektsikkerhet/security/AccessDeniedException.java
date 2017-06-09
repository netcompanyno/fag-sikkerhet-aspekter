package com.netcompany.fagarbeid.aspektsikkerhet.security;

/**
 * Exception that is thrown by the SecurityAspect if all SecurityPredicates on a method return false.
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
