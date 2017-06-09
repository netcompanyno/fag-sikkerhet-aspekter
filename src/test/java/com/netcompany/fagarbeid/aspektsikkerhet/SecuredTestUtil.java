package com.netcompany.fagarbeid.aspektsikkerhet;

import com.netcompany.fagarbeid.aspektsikkerhet.security.Secured;
import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.SecurityPredicate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SecuredTestUtil {
    public static void checkSecuredOnMethod(Object object,
                                        String methodName,
                                        Class<? extends SecurityPredicate>... predicates) {
        final Method[] declaredMethods = object.getClass().getDeclaredMethods();

        final List<Method> matchingMethods = Arrays.stream(declaredMethods)
                .filter(m -> m.getName().equals(methodName))
                .collect(Collectors.toList());

        if (matchingMethods.size() != 1) {
            throw new IllegalStateException("Found " + matchingMethods.size() + " methods named " + methodName + " on " + object.getClass().getName());
        }

        final Secured annotation = matchingMethods.get(0).getAnnotation(Secured.class);

        if (annotation == null) {
            throw new IllegalStateException("No Secured annotation on method " + methodName + " on " + object.getClass().getName());
        }

        final List<Class<? extends SecurityPredicate>> predicatesOnMethod = Arrays.asList(annotation.value());
        final List<Class<? extends SecurityPredicate>> predicatesExpected = Arrays.asList(predicates);

        assertThat(predicatesOnMethod).isEqualTo(predicatesExpected);
    }
}
