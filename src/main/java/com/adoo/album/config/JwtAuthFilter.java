package com.adoo.album.config;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	private final SecretKey secretKey;

	public JwtAuthFilter(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String token = extractJwtFromRequest(request);

			if (token != null) {
				Jws<Claims> parsedToken = Jwts.parserBuilder()
						.setSigningKey(secretKey)
						.build()
						.parseClaimsJws(token);

				Claims claims = parsedToken.getBody();

				if (claims.getExpiration() != null && claims.getExpiration().before(new Date())) {
					throw new RuntimeException("Token expirado");
				}

				String username = claims.getSubject();
				if (username != null) {
					// Ponemos username como principal, no userId
					UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(
									username,
									null,
									List.of(new SimpleGrantedAuthority("USER"))
							);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}

		filterChain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
