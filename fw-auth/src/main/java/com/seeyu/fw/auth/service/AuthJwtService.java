//package com.seeyu.fw.auth.service;
//
//import com.jieyundata.micro.auth.common.constant.JwtConstant;
//import com.jieyundata.micro.auth.common.vo.PrincipalData;
//import com.jieyundata.micro.auth.server.config.TokenConfig;
//import com.jieyundata.micro.auth.server.utils.JwtTokenUtils;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Map;
//
///**
// * @author seeyu
// * @date 2019/4/15
// */
//@Slf4j
//@Service
//public class AuthJwtService {
//
//    @Autowired
//    private TokenConfig tokenConfig;
//
//
//    public RSAPublicKey getRsaPublicKey(){
//       return tokenConfig.getRsaKey().getPublicKey();
//    }
//
//
//    public RSAPrivateKey getRsaPrivateKey(){
//        return tokenConfig.getRsaKey().getPrivateKey();
//    }
//
//
//    public String createToken(Map<String, Object> data){
//        return JwtTokenUtils.generateToken(getRsaPrivateKey(), JwtConstant.CUSTOMER_SUBJECT_SIGNATURE, tokenConfig.getExpirationSeconds(), data);
//    }
//
//
//    public PrincipalData<Map<String, Object>> getPrincipal(String token){
//        Claims claims = null;
//        try{
//            claims = JwtTokenUtils.parseJwt(getRsaPublicKey(), token);
//        }
//        //JWT 过期
//        catch (ExpiredJwtException e){
//            log.debug("jwt解析失败", e);
//            if(log.isDebugEnabled()){
//                log.debug("JWT过期: " + token);
//            }
//            PrincipalData<Map<String, Object>> principalData = new PrincipalData<>();
//            principalData.setExpired(true);
//            return principalData;
//        }
//
//        PrincipalData<Map<String, Object>> principalData = new PrincipalData<>();
//        principalData.setPrincipal(claims);
//        principalData.setSubject(claims.getSubject());
//        principalData.setRefreshToken(refreshToken(claims));
//        return principalData;
//    }
//
//
//    private String refreshToken(Claims claims){
//        return createToken(claims);
//    }
//
//}
