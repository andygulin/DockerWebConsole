package docker.web.console.bean;

public class ImageVO {

    @IgnoreProperty
    @Header(name = "created")
    private String created;

    @Header(name = "ID")
    private String id;

    @Header(name = "parentId")
    private String parentId;

    @Header(name = "repoTags")
    private String[] repoTags;

    @IgnoreProperty
    @Header(name = "size")
    private String size;

    @IgnoreProperty
    @Header(name = "virtualSize")
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
