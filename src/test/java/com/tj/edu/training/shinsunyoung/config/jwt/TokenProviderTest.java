package com.tj.edu.training.shinsunyoung.config.jwt;

import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.repository.UserRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("generateToken() 테스트")
    @Test
    void generateTokenTest() {
        // given
        // 테스트 유저 데이터 생성
        User testUser = userRepository.save(User.builder()
                .email("user1@abc.com")
                .password("abcd")
                .build());

        // when
        // 14일 동안 쓸수있는 토큰 생성
        String token = tokenProvider.generateToken(testUser , Duration.ofDays(14));
        System.out.println("*------------------- generateToken : " + token);


        // then
        // 토큰 만든걸 뿌려줌
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id" , Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }

    @DisplayName("validJwtToken() 테스트 : 만료된 토큰이면 유효성 검증에 실패함")
    @Test
    void validJwtTokenTest() {
        // given
        // 테스트 유저 데이터 생성
        User testUser = userRepository.save(User.builder()
                .email("user1@abc.com")
                .password("abcd")
                .build());

        Date now = new Date();

        // JwtFactory 로 대체
//        String token = Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)    // 헤더 type : JWT
//                .setIssuer(jwtProperties.getIssuer())   // application.yml - jwt1@abc.com
//                .setIssuedAt(now)   // 토큰 발행 시간
//                .setExpiration(new Date(now.getTime() - Duration.ofDays(7).toMillis()))    // 토큰 만료 시간이 끝남을 의미
//                .setSubject(testUser.getEmail())   // 토큰 제목 ( User 안에있는 email로 )
//                .claim("id" , testUser.getId())     // 클레임 id
//                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())   // 암호화
//                .compact();
        
        String token = JwtFactory.builder()
                .expiration(new Date(now.getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        boolean result = tokenProvider.validJwtToken(token);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("getAuthentication() : 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        String token = JwtFactory.builder()
                .subject("user@gmail.com")
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);
        String userName = (((UserDetails) authentication.getPrincipal()).getUsername());
        System.out.println("username : " + userName);
        String userPassword = (((UserDetails) authentication.getPrincipal()).getPassword());
        System.out.println("password : " + userPassword);
        List<GrantedAuthority> grantedAuthorityList = ((UserDetails)authentication.getPrincipal()).getAuthorities().stream().collect(toList());
        grantedAuthorityList.forEach((authority) -> System.out.println("auth: " + authority.getAuthority()));

        // then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo("user@gmail.com");
    }

}