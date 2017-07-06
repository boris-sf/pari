package pari.auth;

import java.lang.annotation.Annotation;
import java.security.AccessControlException;

import javax.annotation.security.RolesAllowed;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class SecurityAspect {

	@Autowired
	private AuthService auth;

	@Around("execution(@javax.annotation.security.RolesAllowed * *.*(..))")
	public Object verifyUserRoles(ProceedingJoinPoint jp) throws Throwable {
		for (String role : annotation(jp, RolesAllowed.class).value()) {
			if (auth.currentUser().hasRole(role)) {
				return jp.proceed();
			}
		}
		throw new AccessControlException("Access denied");
	}

	private static <T extends Annotation> T annotation(ProceedingJoinPoint jp, Class<T> type) {
		return ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(type);
	}
}