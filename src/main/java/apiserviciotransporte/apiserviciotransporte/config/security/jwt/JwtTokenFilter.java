package apiserviciotransporte.apiserviciotransporte.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String tokenPrefix = "Bearer";
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailService;
    private final List<String> nonAuthenticatedPaths = List.of("/api/v1/auth/login", "/api/v1/auth/register");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req,
                                    @NonNull HttpServletResponse res,
                                    @NonNull FilterChain filterChain) throws IOException, ServletException {
        String path = req.getRequestURI();
        if(this.nonAuthenticatedPaths.contains(path)) {
            filterChain.doFilter(req, res);
            return;
        }
        try {
            String token = this.getTokenFromRequest(req);
            if(token != null && this.jwtProvider.isTokenValid(token)) {
                String userId = this.jwtProvider.extractSubject(token);
                UserDetails userDetails = this.userDetailService.loadUserByUsername(userId);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error when filtering");
            e.printStackTrace(System.out);
        }
        filterChain.doFilter(req, res);

    }

    private String getTokenFromRequest(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            return authHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
