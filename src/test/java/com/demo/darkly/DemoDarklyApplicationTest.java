package com.demo.darkly;

import com.launchdarkly.sdk.LDContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = GreetingController.class)
@ComponentScan("com.demo.darkly")
public class DemoDarklyApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void index() throws Exception {
		mockMvc.perform(get("/index.html"))
				.andExpect(content().string(containsString("Test your flag settings")));
	}

	@Test
	public void greeting() throws Exception {
		mockMvc.perform(get("/greetings"))
				.andExpect(content().string(containsString("Greetings")));
	}

	@Test
	public void parting() throws Exception {
		mockMvc.perform(get("/partings"))
				.andExpect(content().string(containsString("Saying Goodbye")));
	}
}
