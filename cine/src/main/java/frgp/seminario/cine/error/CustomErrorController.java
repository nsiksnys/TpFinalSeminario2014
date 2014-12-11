package frgp.seminario.cine.error;

import java.security.Principal;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Throwables;

@Controller
class CustomErrorController {
	private static final Logger LOG = LoggerFactory.getLogger(CustomErrorController.class);
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code> element.
	 */
	@RequestMapping("generalError")	
	public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
		String message = getExceptionMessage(request);
		model.addAttribute("errorMessage", message);
		LOG.error("generalError - " + message);
		return "generalError";
	}
	
	@RequestMapping("notAuthorized")
	public String notAuthorized(HttpServletRequest request, HttpServletResponse response, Principal principal, Model model){
		model.addAttribute("usuario", principal.getName());
		LOG.error("notAuthorized - " + getExceptionMessage(request) + " 403");
		return "notAuthorized";
	}
	
	@RequestMapping("notFound")
	public String notFound(HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("pagina", request.getAttribute("javax.servlet.error.request_uri"));
		LOG.error("notFound - " + getExceptionMessage(request));
		return "notFound";
	}

	private String getExceptionMessage(HttpServletRequest request)
	{
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
				
		if (statusCode == null && throwable == null)
			return "";
		
		String exceptionMessage = getExceptionMessage(throwable, statusCode);
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		
		return MessageFormat.format("{0} returned for {1} with message {2}", 
			statusCode, requestUri, exceptionMessage
		); 

	}
	
	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}
}
