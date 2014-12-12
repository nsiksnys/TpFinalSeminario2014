package frgp.seminario.cine.error;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Throwables;

import frgp.seminario.cine.support.web.MessageHelper;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);
	/**
	 * Handle exceptions thrown by handlers.
	 */

	@org.springframework.web.bind.annotation.ExceptionHandler(value = AccessDeniedException.class)	
	public ModelAndView accessDenied(AccessDeniedException exception) {
		LOG.error(Throwables.getRootCause(exception).toString());
		return new ModelAndView("notAuthorized");
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = PersistenceException.class)	
	public String persistance(PersistenceException exception, HttpServletRequest request, RedirectAttributes ra) {
		LOG.error(Throwables.getRootCause(exception).toString());
		MessageHelper.addErrorAttribute(ra, "Por favor intente nuevamente.");
		return "redirect:"+ getPreviousView(request.getHeader("Referer"));
	}
	
	private String getPreviousView(String referer)
	{
		String[] split = referer.split("(\\/\\/)|(\\/)");
		return "/" + split[3] + "/" + split[4];
	}
}