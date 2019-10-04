package com.stackroute.cplayer.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
	final HttpServletRequest request=(HttpServletRequest)req;
	final HttpServletResponse response=(HttpServletResponse) res;
	/*response.addHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");*/
	final String authHeader=request.getHeader("Authorization");
	
	if("OPTIONS".equals(request.getMethod())) {
		response.setStatus(HttpServletResponse.SC_OK);
		chain.doFilter(req, res);
	}
	else {
		if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer")) {
			throw new ServletException("Missing or invalid Authorization header.");
		}
		final String token = authHeader.substring(7);
		final Claims claims=Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		request.setAttribute("claims", claims);
		chain.doFilter(req, res);
	}
		
	}

}
