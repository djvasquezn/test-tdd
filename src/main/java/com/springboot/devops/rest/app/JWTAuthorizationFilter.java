package com.springboot.devops.rest.app;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	private static final String API_KEY = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";
	private static final String API_KEY_HEADER = "X-Parse-REST-API-Key";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authenticationHeader = request.getHeader(API_KEY_HEADER);
		
		if (authenticationHeader == null) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Se requiere autorizacion");
		} else {
			String apiKey = request.getHeader(API_KEY_HEADER);
			
			if (API_KEY.equals(apiKey)) {
				SecurityContextHolder.getContext().setAuthentication(null);
			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Autorizacion denegada");
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
