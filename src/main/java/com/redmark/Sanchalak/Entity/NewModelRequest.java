package com.redmark.Sanchalak.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * NewModelRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewModelRequest {
	NewPayLoad payLoad;
	String prefix = "";
	String postfix = "";
	HashMap<String, String> modelMaps = new HashMap<>(Map.of("llama", "meta-llama/llama-3.2-3b-instruct", "deepseek",
			"deepseek/deepseek-v3-0324", "gemini", "google/gemma-2-9b-it"));

	public NewPayLoad getPayLoad(Query query) {
		payLoad = new NewPayLoad();
		Message message = new Message();
		message.role = "user";
		message.content = query.getQuery();
		payLoad.messages = new ArrayList<>();
		payLoad.messages.add(message);
		payLoad.maxTokens = 500;
		payLoad.model = modelMaps.getOrDefault(query.getModel(), "deepseek/deepseek-v3-0324");
		return payLoad;
	}

}

class Message {
	@JsonProperty("role")
	String role;
	@JsonProperty("content")
	String content;
}

class NewPayLoad {
	@JsonProperty("messages")
	ArrayList<Message> messages;
	@JsonProperty("max_tokens")
	int maxTokens;
	@JsonProperty("model")
	String model;
}
