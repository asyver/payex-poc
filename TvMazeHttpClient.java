package no.syver.tvmaze.http;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;

@Component
public class TvMazeHttpClient {

	@Value("${tvmaze.http.endpoint}")
	private String tvMazeShowsUrl;

	public String doGet(String showname) throws Exception {
		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.followRedirects(HttpClient.Redirect.NORMAL)
				.connectTimeout(Duration.ofSeconds(20))
				.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
				.authenticator(Authenticator.getDefault())
				.build();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(tvMazeShowsUrl + showname))
				.GET()
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());

		return response.body();
	}

	
}