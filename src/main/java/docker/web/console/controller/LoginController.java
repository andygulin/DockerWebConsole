package docker.web.console.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dockerjava.api.DockerClient;

import docker.web.console.Constants;
import docker.web.console.DockerClientManager;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		Object obj = session.getAttribute(Constants.SESSION_KEY);
		if (obj != null) {
			return "redirect:/mgr/main";
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.SESSION_KEY);
		DockerClient client = DockerClientManager.getInstance().getClient();
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/login";
	}
}