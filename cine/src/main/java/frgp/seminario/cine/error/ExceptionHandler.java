package frgp.seminario.cine.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);
	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)	
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
		String fromUrl = request.getContextPath();//TODO: probar
		LOG.error(Throwables.getRootCause(exception).toString());
		System.out.println(Throwables.getStackTraceAsString(exception));
		return modelAndView;
	}
}