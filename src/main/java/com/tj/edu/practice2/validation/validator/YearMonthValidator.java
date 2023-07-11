package com.tj.edu.practice2.validation.validator;

import com.tj.edu.practice2.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth , String> {

    private String pattern;

    @Override   // 초기화 하는 곳
    public void initialize(YearMonth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        // YearMonth 이노테이션에 있는 pattern 메소드 값으로 세팅
        this.pattern = constraintAnnotation.pattern();
    }

    @Override   // 형식을 만들어주는 곳
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // pattern 기본값은 YYYYMM
        try {
            LocalDate.parse(value + "01", DateTimeFormatter.ofPattern(this.pattern));
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
