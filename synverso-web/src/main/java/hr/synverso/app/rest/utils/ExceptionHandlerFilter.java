package hr.synverso.app.rest.utils;

import hr.synverso.common.exceptions.AppException;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (AppException e) {
			response.getWriter().write( ResponseMessage.packageAndJsoniseError(e.getError()));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		catch (JwtException e) {
//			response.getWriter().write( ResponseMessage.packageAndJsoniseError(TimunError.SESSION_EXPIRED));
//		}
	}
}