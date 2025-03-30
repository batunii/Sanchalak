package com.redmark.Sanchalak.Services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service
 */

import com.google.gson.Gson;
import com.redmark.Sanchalak.SanchConsts;
import com.redmark.Sanchalak.Entity.NewModelRequest;
import com.redmark.Sanchalak.Entity.Query;
import com.redmark.Sanchalak.Entity.Request;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	SanchConsts consts;

	Request getRequest = new Request();
	NewModelRequest request = new NewModelRequest();

	public String getResponse(Query query) throws Exception {
		Gson gson = new Gson();
		String[] requestHeaders = { "Authorization", "Bearer " + consts.getAPI_KEY(),
				"Content-Type", "application/json" };
		// getRequest.setRequestHeaders(requestHeaders);
		URI apiUri = new URI(consts.getLLM_URI());
		HttpRequest gRequest = HttpRequest.newBuilder()
				.uri(apiUri).headers(requestHeaders).POST(BodyPublishers.ofString(
						gson.toJson(request.getPayLoad(query))))
				.build();
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> resp = httpClient.send(gRequest, BodyHandlers.ofString());
		System.out.println(gson.toJson(request.getPayLoad(query)));
		System.out.println(resp.body());
		return resp.body();
	}

	public void updatePostFixAndPrefix(String prefix, String postfix) {
		if (!prefix.isBlank()) {
			getRequest.setPrompt_prefix(prefix);
			request.setPrefix(prefix);
		}
		if (!postfix.isBlank()) {
			getRequest.setPrompt_suffix(postfix);
			request.setPostfix(postfix);
		}
	}

}
