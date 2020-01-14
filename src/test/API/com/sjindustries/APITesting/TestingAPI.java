package com.sjindustries.APITesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

public class TestingAPI {

	private static final String URL_SECURED_BY_BASIC_AUTHENTICATION = "https://dev-iib-ap01:6530/setAppointment";
	private static final String DEFAULT_USER = "DEVIIBTSTER";
	private static final String DEFAULT_PASS = "kML2x8X$*AVt";

	private CloseableHttpResponse response;

	@Test
	public final void whenExecutingBasicGetRequestWithBasicAuthenticationEnabled_thenSuccess() throws IOException {

		File requestFile = new File("C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\request\\setAppoinment.xml");
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		response = client.execute(new HttpPost(URL_SECURED_BY_BASIC_AUTHENTICATION));

		response.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		final int statusCode = response.getStatusLine().getStatusCode();
//        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
		System.out.println(statusCode);
	}
}
