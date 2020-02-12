package dev.shermende;

import dev.shermende.security.token.TokenAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public TokenAuthentication home(
        @AuthenticationPrincipal TokenAuthentication authentication
    ) {
        return authentication;
    }

}
