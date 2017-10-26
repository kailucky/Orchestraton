package com.nsfocus.orchestration.entity;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.HEAD;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Sherry
 * Reponse like :	
 *{
    	"status": "True",
    	"msg": {
        	"remote_file_inclusion": "True"
    	}
	}
 */

public class ResponseEntity {
	String status;
	String msg;
	
	public ResponseEntity(String result) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(result);
		this.status = node.path("status").asText();
		this.msg = node.path("msg").toString();
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public String getMsg(){
		return this.msg;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public void setMsg(String msg){
		this.msg = msg;
	}
	// Test
	public static void main(String[] args) throws IOException{		
		String result = "{\"status\":\"True\",\"msg\":{\"xss\": \"True\", \"remote_file_inclusion\": \"True\"}}";
		ResponseEntity res = new ResponseEntity(result);
	}
}
