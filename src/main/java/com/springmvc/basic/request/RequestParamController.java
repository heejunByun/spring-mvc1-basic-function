package com.springmvc.basic.request;

import com.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} , age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 리턴값 ok 라는 문자를 바로 반환한다 (view 를 찾지않음)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
            ) {
        log.info("username = {} , age = {}", memberName, memberAge);
        return "ok";
    }

    //요청 파라미터와 이름이 같을 경우
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    // 인간의 욕심은 끝이 없는 경우
    // String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        //int age = null 이 될 수 없음
        //Integer 은 객체이기때문에 null 이 허용
        ///request-param-required&username= 으로 보내지면 빈 값으로 호출이된다.
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        //빈문자까지 guest 로 반환해준다.
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {} , age = {}", paramMap.get("username") , paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1-org")
    public String modelAttributeV1Org(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        log.info("username = {} , age = {}", helloData.getUsername() , helloData.getAge());
        log.info("helloData = {}", helloData.toString());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {} , age = {}", helloData.getUsername() , helloData.getAge());
        log.info("helloData = {}", helloData.toString());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { //@ModelAttribute 생략가능하지만 비추
        /**
         * @ModelAttribute 는 생략할 수 있다. (비추)
         * 그런데 @RequestParam 도 생략할 수 있으니 혼란이 발생할 수 있다.
         * 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
         * String , int , Integer 같은 단순 타입 = @RequestParam
         * 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
         */
        log.info("username = {} , age = {}", helloData.getUsername() , helloData.getAge());
        log.info("helloData = {}", helloData.toString());
        return "ok";
    }
}
