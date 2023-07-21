package com.tj.edu.training.shinsunyoung.config.oauth;


import com.tj.edu.training.shinsunyoung.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.web.util.WebUtils;

/**
 * desc: OAuth2에 세션이 아닌 쿠키에 저장해서 쓸 수 있도록 관련 상태를 저장을 지원하는 용도
 */
public class OAuth2AuthorizationRequestBasedOnCookieRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    public final static String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    private final static int COOKIE_EXPIRE_SECONDS = 18000;

    /**
     * desc: 쿠키값 가져오기
     * @param request the {@code HttpServletRequest}
     * @return
     */
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        return CookieUtil.deserialize(cookie, OAuth2AuthorizationRequest.class);
    }

    /**
     * desc: 쿠키값 저장하기
     * @param authorizationRequest the {@link OAuth2AuthorizationRequest}
     * @param request the {@code HttpServletRequest}
     * @param response the {@code HttpServletResponse}
     */
    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null) {
            removeAuthorizationRequestCookies(request, response);
            return;
        }

        CookieUtil.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookieUtil.serialize(authorizationRequest), COOKIE_EXPIRE_SECONDS);
    }

    /**
     * desc: 쿠키값 삭제하기
     * @param request the {@code HttpServletRequest}
     * @param response the {@code HttpServletResponse}
     * @return
     */
    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
    }
}
