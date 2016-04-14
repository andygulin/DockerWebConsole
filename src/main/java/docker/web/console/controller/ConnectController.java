package docker.web.console.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import docker.web.console.Constants;
import docker.web.console.DockerClientManager;
import docker.web.console.bean.Response;

@Controller
public class ConnectController {

	@ResponseBody
	@RequestMapping(value = "connect", method = RequestMethod.POST)
	public Response<String> connect(@RequestParam("serverUrl") String serverUrl, HttpSession session) {
		Response<String> response = null;
		try {
			DockerClientManager.getInstance().setClient(serverUrl);
			response = new Response<>(true, "");
			session.setAttribute(Constants.SESSION_KEY, serverUrl);
		} catch (Exception e) {
			response = new Response<>(false, "");
		}
		return response;
	}
}