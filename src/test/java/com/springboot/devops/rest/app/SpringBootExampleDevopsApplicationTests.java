package com.springboot.devops.rest.app;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.devops.rest.app.domain.Request;

import net.minidev.json.JSONObject;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WebMvcTest
@RunWith(SpringRunner.class)
class SpringBootExampleDevopsApplicationTests {
	
	
//	@Autowired
//    private TestRestTemplate restTemplate;
	
	@Autowired
	MockMvc mock;
	
	private static String postRequest = "{\n" +
	        "  \"message\": \"This is a test\",\n" +
	        "  \"to\": \"Juan Perez\",\n" +
	        "  \"from\": \"Rita Asturia\",\n" +
	        "  \"timeToLifeSec\": 45 \n" +
	        "}";

	@Test
	void postRequestTest() throws Exception {
		String toMessage = "Juan Perez";
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("message", "This is a test");
		requestParams.put("to", toMessage);
		requestParams.put("from", "This is a test");
		requestParams.put("timeToLifeSec", 45L);
		
//		RestAssured.baseURI = "http://localhost:8080";
//		RestAssured.given().urlEncodingEnabled(true)
//	        .header("Content-Type", "application/json")
//	        .header("X-Parse-REST-API-Key", "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c")
////	        .body(requestParams.toJSONString())
//	        .body(postRequest)
//	        .post("/message/send")
//	        .then().statusCode(200);
////	        .assertThat().body("message", new ResponseAwareMatcher() {
////				@Override
////				public Matcher matcher(ResponseBody response)  {
////					return equalTo(String.format("Hello %syour message will be send", toMessage));
////				}
////           });
//		body("message", equalTo(String.format("Hello %syour message will be send", toMessage)));
		
		mock.perform(post("/rest/send")
		           .contentType(MediaType.APPLICATION_JSON)
//		           .header("X-Parse-REST-API-Key", "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c")
		           .content(new ObjectMapper().writeValueAsString(new Request("This is a test", toMessage, "Rita Asturia", 45L))) 
//		           .content(postRequest)
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("message").value(String.format("Hello %s your message will be send", toMessage))) 
		           ; 
	}

}
