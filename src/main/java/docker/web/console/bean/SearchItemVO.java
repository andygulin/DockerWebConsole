package docker.web.console.bean;

public class SearchItemVO {

	@Header(name = "星数")
	private int starCount;

	@Header(name = "是否官方")
	private String official;

	@Header(name = "名称")
	private String name;

	@Header(name = "简介")
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
