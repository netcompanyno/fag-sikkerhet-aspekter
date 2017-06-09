package com.netcompany.fagarbeid.aspektsikkerhet.security.predicates;


import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FirstParamIsAleksi implements SecurityPredicate {
    /**
     * Checks if the first parameter to the method is the string aleksi
     *
     * @param params The parameters the method is receiving.
     * @return True if the first parameter is aleksi, false otherwise.
     */
    @Override
    public boolean checkAccess(final Map<String, Object> params) {
        if (params.size() < 1) {
            return false;
        }

        return params.values().toArray()[0].toString().toLowerCase().equals("aleksi");
    }
}
