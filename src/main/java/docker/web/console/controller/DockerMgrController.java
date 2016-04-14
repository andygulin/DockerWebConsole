package docker.web.console.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import docker.web.console.bean.FieldUtil;
import docker.web.console.bean.ImageVO;
import docker.web.console.bean.Response;
import docker.web.console.bean.SearchItemVO;
import docker.web.console.service.DockerMgrService;

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
		Response<String> response = null;
		try {
			dockerMgrService.removeImage(imageId);
			response = new Response<String>(true, "");
		} catch (Exception e) {
			response = new Response<String>(false, "");
		}
		return response;
	}
}