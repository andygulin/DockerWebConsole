package docker.web.console.bean;

public class SearchItemVO {

    @Header(name = "starCount")
    private int starCount;

    @Header(name = "official")
    private String official;

    @Header(name = "name")
    private String name;

    @Header(name = "desc")
    private String description;

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
