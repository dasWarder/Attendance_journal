package by.itechart.web.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public String getLoggedUser() {
        UserDetails customer = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return customer.getUsername();
    }
}
