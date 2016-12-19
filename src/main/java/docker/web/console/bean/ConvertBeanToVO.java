package docker.web.console.bean;

import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.SearchItem;
import docker.web.console.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class ConvertBeanToVO {

    private ConvertBeanToVO() {
        throw new UnsupportedOperationException();
    }

    public static ImageVO image(Image image) {
        ImageVO imageVO = new ImageVO();
        BeanUtils.copyProperties(image, imageVO, FieldUtil.getIgnoreHeader(ImageVO.class));
        imageVO.setCreated(DateFormatUtils.format(new Date(image.getCreated()), Constants.DEFAULT_DATAFORMAT_STR));
        imageVO.setSize(FileUtils.byteCountToDisplaySize(image.getSize()));
        imageVO.setVirtualSize(FileUtils.byteCountToDisplaySize(image.getVirtualSize()));
        return imageVO;
    }

    public static SearchItemVO searchItem(SearchItem item) {
        SearchItemVO itemVO = new SearchItemVO();
        itemVO.setDescription(item.getDescription());
        itemVO.setName(item.getName());
        itemVO.setStarCount(item.getStarCount());
        itemVO.setOfficial(Official.getText(item.isOfficial()));
        return itemVO;
    }
}
