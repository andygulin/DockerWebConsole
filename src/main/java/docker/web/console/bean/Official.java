package docker.web.console.bean;

public enum Official {

	TRUE(true, "Yes"), FALSE(false, "No");

	private final boolean isOfficial;
	private final String text;

	private Official(boolean isOfficial, String text) {
		this.isOfficial = isOfficial;
		this.text = text;
	}

	public boolean isOfficial() {
		return isOfficial;
	}

	public String getText() {
		return text;
	}

	public static String getText(boolean isOfficial) {
		String ret = "";
		for (Official official : Official.values()) {
			if (official.isOfficial() == isOfficial) {
				ret = official.getText();
				break;
			}
		}
		return ret;
	}
}
