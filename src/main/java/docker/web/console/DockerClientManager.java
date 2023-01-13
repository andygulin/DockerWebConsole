package docker.web.console;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;

import java.time.Duration;

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
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerConfig(serverUrl).build();
        ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        this.client = DockerClientImpl.getInstance(config, httpClient);
    }

    private static final class Holder {
        private static final DockerClientManager INSTANCE = new DockerClientManager();
    }
}