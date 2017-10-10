package kr.co.bit.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	
	
	@Around("execution(* kr.co.bit..*.*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object result = null;
		String pattern = "[yyyy-MM-dd hh:mm:ss]";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		System.out.println(sdf.format(new Date())+joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"시작()");
		
		result = joinPoint.proceed();
		
		System.out.println(sdf.format(new Date())+joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"종료()");
		
		return result;
	}
}
