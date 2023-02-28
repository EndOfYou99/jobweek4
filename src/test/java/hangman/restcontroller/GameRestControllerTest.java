package hangman.restcontroller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameRestControllerTest {

	private static final String url = "http://localhost:8081/api/v1/games";

	@Test
	public void test() {

		given().baseUri(url).when().get("/ongoing").then().statusCode(200);
	}

}
