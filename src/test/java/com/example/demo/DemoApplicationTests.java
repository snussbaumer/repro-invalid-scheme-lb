package com.example.demo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT10M")
@WireMockTest(httpPort = 9004)
class DemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void test() {
		stubFor(WireMock.get(urlPathMatching("/test"))
				.willReturn(aResponse()
						.withStatus(200)
						.withBody("hello world")
						.withHeader("Content-Type", "application/json;charset=UTF-8")));


		List<String> response = webTestClient.get().uri("/test").exchange()
				.expectStatus().isOk() //
				.returnResult(String.class)
				.getResponseBody()
				.toStream().toList();

		assertThat(response).contains("hello world");
	}

}
