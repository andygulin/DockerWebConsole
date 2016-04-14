package docker.web.console;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerClientManager {

	private DockerClientManager() {
	}

	private static final class Holder {
		private static DockerClientManager INSTANCE = new DockerClientManager();
	}

	public static DockerClientManager getInstance() {
		return Holder.INSTANCE;
	}

	private DockerClient client;

	public DockerClient getClient() {
		return client;
	}

	public void setClient(String serverUrl) {
		this.client = DockerClientBuilder.getInstance(serverUrl).build();
	}
}