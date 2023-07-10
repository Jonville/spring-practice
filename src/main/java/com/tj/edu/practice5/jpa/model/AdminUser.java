package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AdminUser {
    @Id
    private String id;
    private String passwd;
    @NonNull
    private String name;
    private String nick;
    private String gender;
    private String birth;
    private String phone;
    private String email;
    private String addr;
    private String quiz;
    private String answer;
    private String grade;
    private String cgrade;
    private String point;
    private String bancnt1;
    private String bancnt2;
    private String pwcont;
    private String auth1Yn;
    private String auth2Yn;
    private String creaflg;
    private String adminflg;
    private String ustatus;
    private String cdate;
    private String creatAt;
    private String updateAt;

    private int dno;
    private String cid;
    private String cnum;
    private String cinfo;

}
