package boot;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class AppConfig {

	private HashMap<String, Object> m_ConfigMap;

	@SuppressWarnings("unchecked")
	public AppConfig(String fileConfigPath) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileConfigPath));
		m_ConfigMap = (HashMap<String, Object>) decoder.readObject();
		decoder.close();
	}
	
	public String getBaseUrl(){
		return (String)m_ConfigMap.get("BaseUrl");
	}

	public String getApiKey(){
		return (String)m_ConfigMap.get("ApiKey");
	}

}
