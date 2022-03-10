package util;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public final class Configuration {
	private static Configuration instance;
	private static String configFile = "config.json";
	
	private static boolean canWrite = false;
	
	private String author;
	private String version;
	private String title;
	private String description;
	private String language;
	private Date lastRun;
	
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; this.save(); }

	public String getVersion() { return version; }
	public void setVersion(String version) { this.version = version; this.save(); }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; this.save(); }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; this.save(); }

	public String getLanguage() { return language; }
	public void setLanguage(String language) { this.language = language; this.save(); }
	
	public Date getLastRun() { return lastRun; }
	private void setLastRun(Date lastRun) { this.lastRun = lastRun; this.save(); }
	
	public static Configuration getInstance() {
		if(Configuration.instance == null) {
			if(Files.exists(Paths.get(Configuration.configFile))){
				Configuration.instance = FileHandler.readJsonAsObject(Configuration.configFile, Configuration.class);
			} else {
				Configuration.instance = new Configuration();
				Configuration.instance.setAuthor("Ray");
				Configuration.instance.setTitle("Glamazon");
				Configuration.instance.setDescription("Desktop Shop App For Everyone!");
				Configuration.instance.setVersion("0.0.1");
				Configuration.instance.setLanguage("EN");
				Configuration.instance.setLastRun(new Date());
				Configuration.canWrite = true;
				Configuration.instance.save();
			}
		}
		return Configuration.instance;
	}
	
	private void save() {
		if(canWrite)
			FileHandler.writeObjectAsJson(Configuration.configFile, Configuration.instance);
	}
	
	private Configuration() {}
	
	@Override
	public String toString() {
		return "Configuration [getAuthor()=" + getAuthor() + ", getVersion()=" + getVersion() + ", getTitle()="
				+ getTitle() + ", getDescription()=" + getDescription() + ", getLanguage()=" + getLanguage()
				+ ", getLastRun()=" + getLastRun() + "]";
	}
}
