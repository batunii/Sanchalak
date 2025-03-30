package com.redmark.Sanchalak;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import jakarta.annotation.Resource;
import lombok.NoArgsConstructor;

/**
 * Constants
 */
@NoArgsConstructor
@Configuration
@PropertySource("classpath:application.properties")
public class SanchConsts {
	@Resource
	public Environment env;

	private String API_KEY;
	private String LLM_URI;

	public String getAPI_KEY() {
		API_KEY = env.getProperty("huggingface.api.key");
		return API_KEY;
	}

	public String getLLM_URI() {
		LLM_URI = env.getProperty("huggingface.api.url");
		return LLM_URI;
	}
}
