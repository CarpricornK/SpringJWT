package kopo.poly.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserInfoEntity2 {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라감
    private String userId;

    @Column(name="userName")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="addr1")
    private String addr1;

    @Column(name="addr2")
    private String addr2;

    @Column(name="regId")
    private String regId;

    @Column(name="regDt")
    private String regDt;

    @Column(name="chgId")
    private String chgId;

    @Column(name="chgDt")
    private String chgDt;

    @Column(name="roles")
    private String roles;


}
