package com.example.demo;

import static org.mockito.Mockito.stub;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRestDemoApplicationTests {

	private RestTemplate restTemplet;

	@Rule
	public WireMockRule rule = new WireMockRule(8090);

	@Before
	public void before() {
		restTemplet = new RestTemplate();
	}

	@Test
	public void contextLoads() {

		stubFor(get(urlEqualTo("/greeting")).willReturn(aResponse().withStatus(HttpStatus.OK.value())));

		String msg = restTemplet.getForObject("http://localhost:8050/greeting", String.class);
		verify(getRequestedFor(urlMatching("/greeting")));

	}

}
