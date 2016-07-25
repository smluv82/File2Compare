package com.smluv82.file2compare.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.smluv82.file2compare.constants.File2CompareConstants;

@Aspect
public class File2CompareAspect {
	private static Logger logger = LoggerFactory.getLogger(File2CompareConstants.LOG_FILE2COMPARE);

	@Around("execution(* com.smluv82.file2compare.controller..*.*(..)) or execution(* com.smluv82.file2compare.*.*(..)) or execution(* com.smluv82.file2compare.*.*(..))")
	public Object confirmTargetMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
		final StringBuilder targetNameBuilder = new StringBuilder();
//		targetNameBuilder.append(joinPoint.getTarget().getClass().getSimpleName());
		targetNameBuilder.append(joinPoint.getSignature().getDeclaringTypeName());	//패키지+클래스명
		targetNameBuilder.append(".");
		targetNameBuilder.append(joinPoint.getSignature().getName());	//메서드명
		targetNameBuilder.append("()");

		logger.debug("{} - start.", targetNameBuilder.toString());

		//수행 시간 확인
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final Object proceed = joinPoint.proceed();

		stopWatch.stop();

		//log level trace일 경우 parameter 확인
		if(logger.isTraceEnabled()) {
			final StringBuilder paramsBuilder = new StringBuilder();
			paramsBuilder.append("'s parameters : ");

			final Object[] args = joinPoint.getArgs();

			for(final Object arg : args) {
				paramsBuilder.append(arg).append(",");
			}

			if(args.length > 0) {
				logger.trace("{}{}", targetNameBuilder.toString(), paramsBuilder.deleteCharAt(paramsBuilder.length() - 1).toString());
			}else {
				logger.trace("{}'s no parameters.", targetNameBuilder.toString());
			}
		}

		final StringBuilder timerBuilder = new StringBuilder(23);
		timerBuilder.append("'s execution time : ");
		timerBuilder.append(stopWatch.getTotalTimeMillis());
		timerBuilder.append(" ms");

		logger.debug("{} - end.", targetNameBuilder.toString());
		logger.debug("{}{}", targetNameBuilder.toString(), timerBuilder.toString());

		return proceed;
	}
}
