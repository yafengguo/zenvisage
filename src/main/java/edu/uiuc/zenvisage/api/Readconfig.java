package edu.uiuc.zenvisage.api;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Readconfig {
	
	static Properties props = new Properties();
	static String configloc = "/src/main/java/edu/uiuc/zenvisage/api/config.properties";
	static File f = new File("");
	static String path = f.getAbsoluteFile().getParent()+configloc;
	
	static{
			try {
				FileInputStream in = new FileInputStream(path);
				props.load(in);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	static public int getPort(){
		return Integer.valueOf(props.getProperty("port"));
	}
	
	static public String getMetatable(){
		return props.getProperty("metatable");
	}
	
	static public String getMetafilelocation(){
		return props.getProperty("metafilelocation");
	}
	
	static public String getUsername(){
		return props.getProperty("username");
	}
	
	static public String getPassword(){
		return props.getProperty("password");
	}

	
}