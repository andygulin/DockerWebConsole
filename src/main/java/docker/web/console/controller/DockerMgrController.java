package docker.web.console.controller;

import com.alibaba.fastjson2.JSON;
import docker.web.console.bean.FieldUtil;
import docker.web.console.bean.ImageVO;
import docker.web.console.bean.Response;
import docker.web.console.bean.SearchItemVO;
import docker.web.console.service.DockerMgrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mgr")
public class DockerMgrController {

    @Autowired
    private DockerMgrService dockerMgrService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(Model model) {
        model.addAttribute("info", JSON.toJSONString(dockerMgrService.getInfo()));
        return "mgr/info";
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version(Model model) {
        model.addAttribute("version", JSON.toJSONString(dockerMgrService.getVersion()));
        return "mgr/version";
    }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public String images(Model model) {
        model.addAttribute("imageVOs", dockerMgrService.getImages());
        model.addAttribute("headers", FieldUtil.getHeader(ImageVO.class));
        return "mgr/images";
    }

    @RequestMapping(value = "/image/search", method = RequestMethod.GET)
    public String searchImages(@RequestParam("term") String term, Model model) {
        if (StringUtils.isNotEmpty(term)) {
            model.addAttribute("items", dockerMgrService.searchImages(term));
            model.addAttribute("headers", FieldUtil.getHeader(SearchItemVO.class));
        }
        return "mgr/search_images";
    }

    @ResponseBody
    @RequestMapping(value = "/image/remove/{imageId}", method = RequestMethod.POST)
    public Response<String> removeImage(@PathVariable("imageId") String imageId) {
        Response<String> response;
        try {
            dockerMgrService.removeImage(imageId);
            response = new Response<>(true, "");
        } catch (Exception e) {
            response = new Response<>(false, "");
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/image/pull/{repository}", method = RequestMethod.POST)
    public Response<String> pullImage(@PathVariable("repository") String repository) {
        Response<String> response;
        try {
            dockerMgrService.pullImage(repository);
            response = new Response<>(true, "");
        } catch (Exception e) {
            response = new Response<>(false, "");
        }
        return response;
    }
}