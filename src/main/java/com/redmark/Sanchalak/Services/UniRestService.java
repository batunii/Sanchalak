package com.redmark.Sanchalak.Services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.redmark.Sanchalak.SanchConsts;
import com.redmark.Sanchalak.Entity.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UniRestService
 */
@Service
public class UniRestService {

	@Autowired
	SanchConsts consts;

	Request getRequest = new Request();
	public String getResponse(String query) throws Exception
	{
		Gson gson = new Gson();
		System.out.println(gson.toJson(getRequest.getPayLoad(query)));
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post(consts.getLLM_URI())
  .header("Content-Type", "application/json")
  .header("Authorization", "Bearer "+consts.getAPI_KEY())
  .body("{\n \"messages\": \n  ").asString();
  
System.out.println("{\n    \"inputs\": \"<|begin_of_text|><|start_header_id|>system<|end_header_id|>You are a helpful and smart assistant. You accurately provide answer to the provided user query.<|eot_id|><|start_header_id|>user<|end_header_id|> Here is the query :"+query+". Provide precise and concise answer.<|eot_id|><|start_header_id|>assistant<|end_header_id|>\",\n    \"parameters\": {\n        \"max_new_tokens\": 5000,\n        \"temperature\": 0.01,\n        \"top_k\": 50,\n        \"top_p\": 0.95,\n        \"return_full_text\": false\n    }\n}");
		return response.getBody();

	}
	
}
