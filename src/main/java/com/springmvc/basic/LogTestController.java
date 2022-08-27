package com.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 * @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
 * @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 * 따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히
 * 설명한다.
 *
 * @Slf4j
 * private final Logger log = LoggerFactory.getLogger(getClass());
 * 선언 없이 log 사용 가능
 */
@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);

        /**
         * 올바른 로그 사용법
         * log.debug("data="+data)
         * 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다.
         * 결과적으로 문자 더하기 연산이 발생한다.
         * log.debug("data={}", data)
         * 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이
         * 발생하지 않는다.
         */
        log.trace("trace log my " + name); // +를 사용하게되면 연산을 무조건 하기 때문에 cpu와 메모리를 사용하게된다. (쓸모없는 리소스)
        log.trace("trace log = {}" , name);

        /**
         * Log Level (trace , debug, info, warn, error)
         * 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에
         * 맞게 조절할 수 있다.
         */
        log.trace("trace log = {}" , name);
        log.debug(" debug log = {}" , name);
        log.info(" info log = {} ", name);
        log.warn(" warn log = {}" , name);
        log.error(" error log = {}" , name);
        return "ok";
    }

}
