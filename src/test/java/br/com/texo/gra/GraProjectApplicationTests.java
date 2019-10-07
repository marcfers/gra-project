package br.com.texo.gra;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GraProjectApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testScenario01() throws JSONException {
		final String producersInJson = "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final ResponseEntity<String> response = this.restTemplate.getForEntity("/awardsInterval/betweenTwoAwards", String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(producersInJson, response.getBody(), false);
	}

}
