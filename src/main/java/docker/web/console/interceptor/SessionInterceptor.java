package docker.web.console.interceptor;

import com.github.dockerjava.api.DockerClient;
import docker.web.console.Constants;
import docker.web.console.DockerClientManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Constants.SESSION_KEY);
        if (obj == null) {
            response.sendRedirect(request.getServletContext().getContextPath());
            return false;
        }

        DockerClient client = DockerClientManager.getInstance().getClient();
        if (client == null) {
            response.sendRedirect(request.getServletContext().getContextPath());
            return false;
        }
        return true;
    }
}