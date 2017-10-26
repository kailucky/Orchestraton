package com.nsfocus.orchestration.developer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsfocus.orchestration.handler.JobHandler;
import com.nsfocus.orchestration.utils.http.JsonUtils;

public class IdsFw implements JobHandler{

	@Override
	public String processFunction(String appOutput) {
		// TODO Auto-generated method stub
		Map<String,String> result = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode json = mapper.readTree(appOutput);
			Iterator iter = json.iterator();
			while(iter.hasNext()){
				JsonNode js = (JsonNode)iter.next();
				String type = js.path("type").toString();
				//["Possible TCP DoS"]
				String detail = js.path("detail").toString();
				//["192.168.19.43:15679 -> 192.168.19.44:80"]
				System.out.println(type + detail);
				if(type.equals("[\"Possible TCP DoS\"]")){
					String[] content = detail.split(" -> ");
					String[] source = content[0].split(":");
					String dst = content[1];
					result.put("source", source[0].replace("[\"", ""));
					result.put("dst", dst.replace("\"]", ""));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonUtils.encodeMapToJson(result);
	}

}
