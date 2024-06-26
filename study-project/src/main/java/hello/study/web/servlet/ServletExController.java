package hello.study.web.servlet;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ServletExController {
	
	@GetMapping("/error-ex")
	public void errorEx() {
		throw new RuntimeException("예외 발생!");
	}
	
	@GetMapping("/error-404")
	public void error404(HttpServletResponse response) throws IOException {
		response.sendError(404);
		
	}
	
	@GetMapping("/error-500")
	public void error500(HttpServletResponse response) throws IOException {
	 response.sendError(500);
	}

}
