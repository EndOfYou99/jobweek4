package hangman.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GameExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String nestedServletExceptionHandler(IllegalArgumentException iae, Model model) {
		String errorMessage = iae.getLocalizedMessage();

		model.addAttribute("message", errorMessage);

		return "notFound";
	}

	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandler(Exception e, Model model) {
		String errorMessage = e.getLocalizedMessage();

		model.addAttribute("message", errorMessage);
		return "error";
	}

}
