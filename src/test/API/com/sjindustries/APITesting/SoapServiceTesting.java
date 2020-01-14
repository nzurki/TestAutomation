package com.sjindustries.APITesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

public class SoapServiceTesting {

//String endpoint ="http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal";
//String endpoint= "https://dev-iib-ap01:6530/setAppointment";
String request= "C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\request\\setAppoinment.xml";
//String response="C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\response";

//	File requestFile = new File("C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\request\\setAppoinment.xml");
	
	private static final String SAMPLE_URL = "https://dev-iib-ap01:6530/setAppointment";
    private static final String URL_SECURED_BY_BASIC_AUTHENTICATION = "https://dev-iib-ap01:6530/setAppointment";
    private static final String DEFAULT_USER = "DEVIIBTSTER";
    private static final String DEFAULT_PASS = "kML2x8X$*AVt";
    
    @Test(enabled=true)
    public void testService() {
    	
    	  try {
    	      SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
    	      SOAPConnection connection = sfc.createConnection();

    	      MessageFactory mf = MessageFactory.newInstance();
    	      SOAPMessage sm = mf.createMessage();

    	      SOAPHeader sh = sm.getSOAPHeader();
    	      SOAPBody sb = sm.getSOAPBody();
    	      sh.detachNode();
    	      QName bodyName = new QName("http://quoteCompany.com", "GetQuote", "d");
    	      SOAPBodyElement bodyElement = sb.addBodyElement(bodyName);
    	      QName qn = new QName("aName");
    	      SOAPElement quotation = bodyElement.addChildElement(qn);

    	      quotation.addTextNode("TextMode");

    	      System.out.println("\n Soap Request:\n");
    	      sm.writeTo(System.out);
    	      System.out.println();

    	      URL endpoint = new URL("http://yourServer.com");
    	      SOAPMessage response = connection.call(sm, endpoint);
    	      System.out.println(response.getContentDescription());
    	    } catch (Exception ex) {
    	      ex.printStackTrace();
    	    }
    }
   
    @Test(enabled=false)
    public void whenSendPostRequestUsingHttpClient_thenCorrect() throws IOException, AuthenticationException {
    	File requestFile = new File("C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\request\\setAppoinment.xml");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(SAMPLE_URL);
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
        httpPost.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
        
        CloseableHttpResponse response = client.execute(httpPost);
//        final CloseableHttpResponse response = client.execute(request);
//        System.out.println(response.getEntity().getContent());
        System.out.println(response.getStatusLine().getStatusCode());
//        assertThat(response.getStatusLine().getStatusCode());
        
//		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		String line="";
//		StringBuffer sb = new StringBuffer();
//		while((line=br.readLine())!=null)
//		{
//			sb.append(line);
//		}
//		System.out.println(response.getStatusLine().getStatusCode());
//		System.out.println(sb);
//		PrintWriter pw = new PrintWriter("C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\response\\testResponse.json");
//		pw.write(sb.toString());
        client.close();
    }

    @Test(enabled=false)
    public void whenSendPostRequestWithAuthorizationUsingHttpClient_thenCorrect() throws IOException, AuthenticationException {
       
    	File requestFile = new File("C:\\Automation\\SeleniumAutomation\\seleniumTest\\src\\test\\API\\request\\setAppoinment.xml");
    	
    	CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL_SECURED_BY_BASIC_AUTHENTICATION);
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
        httpPost.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));       
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
       
//        httpPost.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));

        final CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getEntity().getContent());
        
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    @Test(enabled=false)
    public void whenPostJsonUsingHttpClient_thenCorrect() throws IOException {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost(SAMPLE_URL + "/detail");

        final String json = "{\"id\":1,\"name\":\"John\"}";
        final StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

//        final CloseableHttpResponse response = client.execute(httpPost);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    
    @Test(enabled=false)
    public void whenSendMultipartRequestUsingHttpClient_thenCorrect() throws IOException {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost(SAMPLE_URL + "/multipart");

        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("username", DEFAULT_USER);
        builder.addTextBody("password", DEFAULT_PASS);
        builder.addBinaryBody("file", new File("src/test/resources/test.in"), ContentType.APPLICATION_OCTET_STREAM, "file.ext");
        final HttpEntity multipart = builder.build();

        httpPost.setEntity(multipart);

//        final CloseableHttpResponse response = client.execute(httpPost);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }
    
}
