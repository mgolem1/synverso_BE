package hr.synverso.app.rest.utils;

import hr.synverso.common.exceptions.AppException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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