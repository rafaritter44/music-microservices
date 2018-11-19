package com.github.ilegra.final_project.app_service.eureka;

import java.net.InetAddress;

import com.github.ilegra.final_project.app_service.util.GetHostIp;

public class RegistryBuilder {
	
	private static final String CONTENT;
	private static final String PORT = "8888";
	private static final String NAME_VARIABLE = "<name>";
	private static final String IP_VARIABLE = "<ip>";
	
	static {
		CONTENT = "{\n" + 
				"    \"instance\": {\n" + 
				"        \"hostName\": \"" + buildInstanceID() + "\",\n" + 
				"        \"app\": \"" + NAME_VARIABLE + "\",\n" + 
				"        \"vipAddress\": \"com.automationrhapsody.eureka.app\",\n" + 
				"        \"secureVipAddress\": \"com.automationrhapsody.eureka.app\",\n" + 
				"        \"ipAddr\": \"" + IP_VARIABLE + "\",\n" + 
				"        \"status\": \"UP\",\n" + 
				"        \"port\": {\"$\": \"" + PORT + "\", \"@enabled\": \"true\"},\n" + 
				"        \"securePort\": {\"$\": \"8443\", \"@enabled\": \"true\"},\n" + 
				"        \"healthCheckUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080/healthcheck\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span><span>/</span><span>healthcheck</span></a>\",\n" + 
				"        \"statusPageUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080/status\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span><span>/</span><span>status</span></a>\",\n" + 
				"        \"homePageUrl\": \"<a class=\\\"vglnk\\\" href=\\\"http://WKS-SOF-L011:8080\\\" rel=\\\"nofollow\\\"><span>http</span><span>://</span><span>WKS</span><span>-</span><span>SOF</span><span>-</span><span>L011</span><span>:</span><span>8080</span></a>\",\n" + 
				"        \"dataCenterInfo\": {\n" + 
				"            \"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\", \n" + 
				"            \"name\": \"MyOwn\"\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
	}
	
	public static String get(String name) {
		return CONTENT.replaceAll(NAME_VARIABLE, name).replaceAll(IP_VARIABLE, getIP());
	}
	
	private static String getIP() {
		return GetHostIp.getMachineIp();
	}
	public static String buildInstanceID() {
		return String.format("%s_%s", getIP(), PORT);
	}
	
}
