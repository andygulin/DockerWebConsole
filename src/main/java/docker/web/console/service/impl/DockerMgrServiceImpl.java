package docker.web.console.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.api.model.Version;
import docker.web.console.DockerClientManager;
import docker.web.console.bean.ConvertBeanToVO;
import docker.web.console.bean.ImageVO;
import docker.web.console.bean.SearchItemVO;
import docker.web.console.service.DockerMgrService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DockerMgrServiceImpl implements DockerMgrService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Info getInfo() {
        DockerClient client = DockerClientManager.getInstance().getClient();
        return client.infoCmd().exec();
    }

    @Override
    public Version getVersion() {
        DockerClient client = DockerClientManager.getInstance().getClient();
        return client.versionCmd().exec();
    }

    @Override
    public List<ImageVO> getImages() {
        DockerClient client = DockerClientManager.getInstance().getClient();
        List<Image> images = client.listImagesCmd().exec();
        List<ImageVO> imageVOs = new ArrayList<>(images.size());
        for (Image image : images) {
            imageVOs.add(ConvertBeanToVO.image(image));
        }
        return imageVOs;
    }

    @Override
    public void removeImage(String imageId) {
        DockerClient client = DockerClientManager.getInstance().getClient();
        client.removeImageCmd(imageId).withForce(true).exec();
    }

    @Override
    public List<SearchItemVO> searchImages(String term) {
        DockerClient client = DockerClientManager.getInstance().getClient();
        final String key = "search:image:term:" + DigestUtils.md5Hex(term);
        if (!redisTemplate.hasKey(key)) {
            List<SearchItem> items = client.searchImagesCmd(term).exec();
            List<SearchItemVO> itemVOs = new ArrayList<>(items.size());
            for (SearchItem item : items) {
                itemVOs.add(ConvertBeanToVO.searchItem(item));
            }
            itemVOs.sort((o1, o2) -> o2.getStarCount() - o1.getStarCount());
            redisTemplate.opsForValue().set(key, JSON.toJSONString(itemVOs), TimeUnit.HOURS.toHours(1L));
        }
        return JSON.parseArray(redisTemplate.opsForValue().get(key), SearchItemVO.class);
    }

    @Override
    public void pullImage(String repository) {
        DockerClient client = DockerClientManager.getInstance().getClient();
        try {
            client.pullImageCmd(repository).exec(new PullImageResultCallback()).awaitCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}