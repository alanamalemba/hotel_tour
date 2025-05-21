package com.amalemba.hotel_tour.util;

import com.amalemba.hotel_tour.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal userDetails) {
            return userDetails.getId();
        }

        throw new IllegalStateException("User is not authenticated");
    }
}
