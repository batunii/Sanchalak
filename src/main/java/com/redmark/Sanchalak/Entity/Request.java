package com.redmark.Sanchalak.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {

	Parameters params = new Parameters();
	String[] requestHeaders;
	String prompt_prefix = "You are a helpful and smart assistant. You accurately provide answer to the provided user query.";
	String prompt_suffix = ". \n Provide precise and concise answer.";

	private String getPrompt(String query) {
		String static_prefix = "<|begin_of_text|><|start_header_id|>system<|end_header_id|>" + prompt_prefix
				+ " <|eot_id|><|start_header_id|>user<|end_header_id|> Here is the query: ";
		String static_suffix = prompt_suffix + "<|eot_id|><|start_header_id|>assistant<|end_header_id|>";
		return static_prefix + query + static_suffix;
	}

	public Payload getPayLoad(String query) {
		Payload payload = new Payload();
		payload.parameters = params;
		payload.inputs = getPrompt(query);
		return payload;
	}

}
