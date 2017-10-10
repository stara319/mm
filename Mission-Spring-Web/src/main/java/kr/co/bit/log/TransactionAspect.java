package kr.co.bit.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAspect {

	/*@Around("execution(* kr.co.bit..*Impl.*(..))")
	public Object (ProceedingJoinPoint joinPoint) throws Throwable{
		
	}*/
}

