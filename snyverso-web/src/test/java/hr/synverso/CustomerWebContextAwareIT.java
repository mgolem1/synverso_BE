package hr.synverso;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import hr.synverso.app.SynversoApp;
import hr.synverso.app.rest.utils.ResponseMessage;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.utils.JsonSimpleHelper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SynversoApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerWebContextAwareIT {

	protected TestRestTemplate restTemplate = new TestRestTemplate();

	protected HttpHeaders headers = new HttpHeaders();

	protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	@LocalServerPort
	private int port;

	protected String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	protected <T> List<T> getPageFromBody(ResponseEntity<String> response, Class<T> dTOClass) throws AppException {
		ResponseMessage message = JsonSimpleHelper.deserialise(response.getBody(), ResponseMessage.class);

		TypeReference<List<T>> valueTypeRef = new TypeReference<List<T>>() {
			@Override
			public Type getType() {
				return super.getType();
			}
		};

		JsonNode tree = JsonSimpleHelper.turnIntoJsonTree(response.getBody());

		List<T> resultList = new ArrayList<>();
		JsonNode content = tree.get("payload").get("content");
		for (int i = 0; i < content.size(); i++) {
			JsonNode node = content.get(i);
			resultList.add(JsonSimpleHelper.deserialise(node, dTOClass));
		}

//		List<T> dTO = JsonSimpleHelper.deserialise(tree.get("payload").get("content"), dTOClass);
		return resultList;
	}

	protected <T> T getDTOObjectFromBody(ResponseEntity<String> response, Class<T> dTOClass) throws AppException {
		ResponseMessage message = JsonSimpleHelper.deserialise(response.getBody(), ResponseMessage.class);


		JsonNode tree = JsonSimpleHelper.turnIntoJsonTree(response.getBody());

		T dTO = JsonSimpleHelper.deserialise(tree.get("payload"), dTOClass);
		return dTO;
	}
}
