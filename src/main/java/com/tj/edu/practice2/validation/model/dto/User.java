package com.tj.edu.practice2.validation.model.dto;

import com.tj.edu.practice2.validation.annotation.YearMonth;
import jakarta.validation.constraints.*;

public class User {

    @NotBlank(message = "이름은 필수 작성해야함")        // Null 이면 안됨~
    //@Size(min = 2 , max = 4 , message = "이름은 2자에서 4자사이로만 작성 가능")
    private String name;

    @Max(value = 200 , message = "나이가 너무 많음")   // age 의 최대값
    @Min(value = 1 , message = "0살 이하는 없음")     // age 의 최소값
    private int age;

    @Email(message = "이메일 형식이 아닙니다.")      // Email 인지 체크해줌
    private String email;

    // 정규 표현식
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "폰번호가 이상함")
    private String phoneNumber;

    @AssertTrue(message = "이메일의 끝 문자열은 .com 을 끝나야 함")
    public boolean isTestSuccess() {
        // 이 메소드 안에 별도의 로직이 필요
        if(email.endsWith(".com")) {
            return true;
        }
        return false;
    }

    // 생년월일
//    @Size(min = 8 , max = 8)    // 무조건 8자리
//    private String birth;

    // default형식 -> 'YYYYMM' -> 199303
//    @YearMonth(parttern = "yyMM") -> 9303
    @YearMonth(message = "YYYYMM형식(숫자6자리)에 맞지 않습니다." )
    private String birth;   // YYYYMM


    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
