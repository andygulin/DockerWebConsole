package docker.web.console.bean;

public class ImageVO {

	@IgnoreProperty
	@Header(name = "创建时间")
	private String created;

	@Header(name = "ID")
	private String id;

	@Header(name = "父ID")
	private String parentId;

	@Header(name = "镜像名称")
	private String[] repoTags;

	@IgnoreProperty
	@Header(name = "镜像大小")
	private String size;

	@IgnoreProperty
	@Header(name = "镜像虚拟大小")
	private String virtualSize;

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String[] getRepoTags() {
		return repoTags;
	}

	public void setRepoTags(String[] repoTags) {
		this.repoTags = repoTags;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getVirtualSize() {
		return virtualSize;
	}

	public void setVirtualSize(String virtualSize) {
		this.virtualSize = virtualSize;
	}

}
