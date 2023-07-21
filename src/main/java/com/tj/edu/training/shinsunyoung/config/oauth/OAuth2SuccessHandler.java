package com.tj.edu.training.shinsunyoung.config.oauth;

import com.tj.edu.training.shinsunyoung.config.jwt.TokenProvider;
import com.tj.edu.training.shinsunyoung.model.RefreshToken;
import com.tj.edu.training.shinsunyoung.model.User;
import com.tj.edu.training.shinsunyoung.repository.RefreshTokenRepository;
import com.tj.edu.training.shinsunyoung.service.UserService;
import com.tj.edu.training.shinsunyoung.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Component
//@EnableWebSecurity
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//public class OAuth2SuccessHandler extends AuthenticationSuccessHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_PATH = "/articles";

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService;

    /**
     * desc: 인증이 성공시 실행된다.
     * @param request the request which caused the successful authentication
     * @param response the response
     * @param chain the {@link FilterChain} which can be used to proceed other filters in
     * the chain
     * @param authentication the <tt>Authentication</tt> object which was created during
     * the authentication process.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        // 인증 유저의 정보의 principal정보를 가져온다.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        // 가져온 principal의 email(username)을 통해서 유저에 대한 모든 정보를 db에서 가져온다
        User user = userService.findByEmail((String) oAuth2User.getAttributes().get("email"));

        // 가져온 user정보를 통해서 refresh토큰을 생성
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        // 생성된 refresh토큰을 refresh테이블에 저장
        saveRefreshToken(user.getId(), refreshToken);
        // 생성된 refresh토큰을 브라우저 쿠키에 저장
        addRefreshTokenToCookie(request, response, refreshToken);

        // 가져온 user정보를 통해서 access토큰을 생성
        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
        // access토큰값과 이동할 targetUrl를 세팅
        String targetUrl = getTargetUrl(accessToken);

        // 기존에 브라우저 저장된 인증 쿠키값을 모두 제거
        clearAuthenticationAttributes(request, response);

        // 설정된 targetUrl로 redirect 이동
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        // 가져온 principal의 email(username)을 통해서 유저에 대한 모든 정보를 db에서 가져온다
//        User user = userService.findByEmail((String) oAuth2User.getAttributes().get("email"));
//
//        // 가져온 user정보를 통해서 refresh토큰을 생성
//        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
//        // 생성된 refresh토큰을 refresh테이블에 저장
//        saveRefreshToken(user.getId(), refreshToken);
//        // 생성된 refresh토큰을 브라우저 쿠키에 저장
//        addRefreshTokenToCookie(request, response, refreshToken);
//
//        // 가져온 user정보를 통해서 access토큰을 생성
//        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
//        // access토큰값과 이동할 targetUrl를 세팅
//        String targetUrl = getTargetUrl(accessToken);
//
//        // 기존에 브라우저 저장된 인증 쿠키값을 모두 제거
//        clearAuthenticationAttributes(request, response);
//
//        // 설정된 targetUrl로 redirect 이동
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
//    }

    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(userId, newRefreshToken));

        refreshTokenRepository.save(refreshToken);
    }

    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();

        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }
}
