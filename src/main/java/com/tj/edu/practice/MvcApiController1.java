package com.tj.edu.practice;

import org.springframework.web.bind.annotation.*;

@RestController
public class MvcApiController1 {
//  @RequestMapping("/test1")
    @GetMapping("/test1")
    public String test1() {
        return "test1입니다!!dsasdsadaf";
    }

    // POST 메소드는 데이터등록하는 메소드
    @PostMapping("/post-test1")
    public String postTest1() {
        return """
                {
                    "name" : "홍길동",
                    "birth" : "1990-05-05"
                }
                """;
    }

    @PutMapping("/put-test1")
    public String putTest1() {
        return "put 은 업데이트 http 메소드 입니다.";
    }

    @DeleteMapping("/delete-test1")
    public String deleteTest1() {
        return "delete 는 삭제 http 메소드다 ";
    }
}
