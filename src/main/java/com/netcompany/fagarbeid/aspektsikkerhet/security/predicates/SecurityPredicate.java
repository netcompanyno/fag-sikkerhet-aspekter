package com.netcompany.fagarbeid.aspektsikkerhet.security.predicates;

import java.util.Map;

/**
 * Security predicate that checks if access to a method should be allowed.
 */
public interface SecurityPredicate {
    /**
     * Checks if access to a method should be allowed based on the parameters to the method.
     * Returning false will not deny access unless all predicates on a method all return false.
     *
     * @param params The parameters the method is receiving.
     * @return True if access should be granted, false if unknown.
     */
    boolean checkAccess(final Map<String, Object> params);
}
