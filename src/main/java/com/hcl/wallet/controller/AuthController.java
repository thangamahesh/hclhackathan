package com.hcl.wallet.controller;

import com.hcl.wallet.security.CustomUserDetails;
import com.hcl.wallet.web.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/auth/login")
    public String login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        boolean isCustomer = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        log.info("Is Customer " + isCustomer);
        boolean isMerchant = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MERCHANT"));
        log.info("Is Merchant " + isMerchant);
        if (isCustomer) return "customer:" + userDetails.getUserId();
        if (isMerchant) return "merchant:" + userDetails.getUserId();
        return "Successful";
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        if (authentication == null) {
            return "anonymous";
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        boolean isCustomer = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        boolean isMerchant = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MERCHANT"));
        if (isCustomer) return "customer:" + userDetails.getUserId();
        if (isMerchant) return "merchant:" + userDetails.getUserId();
        return "unknown:" + userDetails.getUserId();
    }
}
