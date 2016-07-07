package docker.web.console.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import docker.web.console.interceptor.SessionInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/mgr/**");
	}
}