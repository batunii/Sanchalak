package com.redmark.Sanchalak.Services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * Service
 */

import com.google.gson.Gson;
import com.redmark.Sanchalak.SanchConsts;
import com.redmark.Sanchalak.Entity.Request;

@org.springframework.stereotype.Service
public class Service {
	Request getRequest = new Request();

	public String getResponse(String query) throws Exception {
		Gson gson = new Gson();
		String[] requestHeaders = { "Authorization", "Bearer " + SanchConsts.API_KEY,
				"Content-Type", "application/json" };
		getRequest.setRequestHeaders(requestHeaders);
		URI apiUri = new URI(SanchConsts.LLM_URI);
		HttpRequest gRequest = HttpRequest.newBuilder()
				.uri(apiUri).headers(getRequest.getRequestHeaders()).POST(BodyPublishers.ofString(
						gson.toJson(getRequest.getPayLoad(query))))
				.build();
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> resp = httpClient.send(gRequest, BodyHandlers.ofString());
		System.out.println(gson.toJson(getRequest.getPayLoad(query)));
		System.out.println(resp.body());
		return resp.body();
	}

	public void updatePostFixAndPrefix(String prefix, String postfix) {
		if (!prefix.isBlank())
			getRequest.setPrompt_prefix(prefix);
		if (!postfix.isBlank())
			getRequest.setPrompt_suffix(postfix);
	}

}
