package com.netcompany.fagarbeid.aspektsikkerhet.security;

import com.netcompany.fagarbeid.aspektsikkerhet.security.predicates.SecurityPredicate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class SecurityAspect {
    private final ApplicationContext applicationContext;

    public SecurityAspect(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Method that intercepts all calls to methods annotated with Secured.
     *
     * @param proceedingJoinPoint The join point
     * @return The return value of the function being called
     * @throws AccessDeniedException If access to the method is denied by all security predicated
     * @throws Throwable             If the method being invoked throws an exception
     */
    @Around("@annotation(Secured)")
    public Object handleMethodCall(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final List<SecurityPredicate> predicates = getPredicates(proceedingJoinPoint);
        final Map<String, Object> arguments = getArguments(proceedingJoinPoint);

        if (predicates.stream().noneMatch(predicate -> predicate.checkAccess(arguments))) {
            throw new AccessDeniedException("No security predicated returned true");
        }

        return proceedingJoinPoint.proceed();
    }

    /**
     * Gets all the SecurityPredicates that should be evaluated for the method being called.
     *
     * @param proceedingJoinPoint The join point before the method
     * @return The list of SecurityPredicates
     */
    private List<SecurityPredicate> getPredicates(final ProceedingJoinPoint proceedingJoinPoint) {
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method method = signature.getMethod();
        final Secured annotation = (Secured) method.getAnnotation(Secured.class);

        if (annotation == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(annotation.value())
                .map(applicationContext::getBean)
                .collect(Collectors.toList());

    }

    /**
     * Gets a map with the method parameter names and valued for the method being called.
     *
     * @param proceedingJoinPoint The join point before the method
     * @return A map of the method parameter names and values
     */
    private Map<String, Object> getArguments(final ProceedingJoinPoint proceedingJoinPoint) {
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Object[] parameterValues = proceedingJoinPoint.getArgs();
        final String[] parameterNames = signature.getParameterNames();

        final Map<String, Object> arguments = new HashMap<>();

        for (int i = 0; i < parameterNames.length; ++i) {
            arguments.put(parameterNames[i], parameterValues[i]);
        }

        return arguments;
    }
}
