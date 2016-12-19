package docker.web.console;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerClientManager {

    private DockerClient client;

    private DockerClientManager() {
    }

    public static DockerClientManager getInstance() {
        return Holder.INSTANCE;
    }

    public DockerClient getClient() {
        return client;
    }

    public void setClient(String serverUrl) {
        this.client = DockerClientBuilder.getInstance(serverUrl).build();
    }

    private static final class Holder {
        private static DockerClientManager INSTANCE = new DockerClientManager();
    }
}