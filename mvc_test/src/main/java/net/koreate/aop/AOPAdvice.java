package net.koreate.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import net.koreate.vo.BoardVo;

@Component
@Aspect
public class AOPAdvice {
	
	@Before("execution(* net.koreate.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("-------------------------------------");
		System.out.println("--------- START LOG START -----------");

		System.out.println("type : " + jp.getKind());
		System.out.println("parameters : " + Arrays.toString(jp.getArgs()));
		System.out.println("name : " + jp.getSignature().getName());
		System.out.println(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(new Date());
		
		System.out.println("now : " + now);
		System.out.println(System.currentTimeMillis());

		System.out.println("---------- START LOG END ------------");
		System.out.println("-------------------------------------");
	}
	
	@After("execution(* net.koreate.service.MessageService*.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("-------------------------------------");
		System.out.println("---------- END LOG START ------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(new Date());
		
		System.out.println("now : " + now);
		System.out.println(System.currentTimeMillis());

		System.out.println("----------- END LOG END -------------");
		System.out.println("-------------------------------------");
	}
	
	@Around("execution(* net.koreate.service.MessageService*.readMessage(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-------------------------------------");
		System.out.println("---------- Around  START ------------");

		long startTime = System.currentTimeMillis();
		// 전
		
		Object result = pjp.proceed(); // 기준
		
		// 후
		long endTime = System.currentTimeMillis();
		
		System.out.println(pjp.getSignature().getName() + "걸린시간 : " + (endTime - startTime));
		
		System.out.println("---------- Around   END  ------------");
		System.out.println("-------------------------------------");
		return result;
	}
	
	@Around("execution(* net.koreate.service.BoardService*.create(..))")
	public Object insertCheck(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-------------------------------------");
		System.out.println("---------- Around  START ------------");
		String message = "";
		
		try {
			Object[] parameters = pjp.getArgs();
			BoardVo boardVo = (BoardVo) parameters[0];
			//System.out.println(parameters[1]); // ERROR : java.lang.ArrayIndexOutOfBoundsException
			System.out.println("arround : " + boardVo);
			message = (String) pjp.proceed();
			message = "AROUND " + message;
		}
		catch (Exception e) { e.printStackTrace(); message = "AROUND FAILED"; }
		
		System.out.println("---------- Around   END  ------------");
		System.out.println("-------------------------------------");
		return message;
	}
}
