package hangman.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import hangman.business.repo.game.Gamedto;

@SpringBootTest
class GameRestControllerTest2 {

	private static final String url = "http://localhost:8081/api/v1/games";
	private static WebClient webClient;

	@Test
	void testFindById() {

		webClient = WebClient.create(url);
		String id = "00aec234-10ac-4e9b-8dfd-204ac3337a41";

		ResponseEntity<Gamedto> object = webClient.get().uri("/{id}", id).retrieve().toEntity(Gamedto.class).block();

		Gamedto game = object.getBody();

		assertNotNull(game);
		assertEquals(game.getId(), id);
		assertEquals(game.getTries(), 0);
		assertEquals(game.getWord(), "Buzz");
		assertEquals(game.getUser(), "Kaan");

	}

	@Test
	void testMakeTry() {

		webClient = WebClient.create(url);
		String id = "47e6d847-533c-4c9a-b268-b0502da4740f";

		ResponseEntity<Gamedto> object = webClient.put().uri("/{id}?letter=c", id).retrieve().toEntity(Gamedto.class)
				.block();

		Gamedto game = object.getBody();

		assertNotNull(game);
		assertEquals(game.getWord(), "Lucky");
		assertThat(game.getUsedLetters().contains("c"));

	}

	@Test
	void testCreateGame() {
		webClient = WebClient.create(url);

		ResponseEntity<Gamedto> object = webClient.post().uri("/{difficulty}", "Easy").retrieve()
				.toEntity(Gamedto.class).block();

		Gamedto game = object.getBody();

		assertNotNull(game);
		assertEquals(game.getTries(), 0);
		assertEquals(game.getResult(), "N");

	}

	@Test
	void testGetOngoingGames() {
		webClient = WebClient.create(url);

		ParameterizedTypeReference<CollectionModel<Gamedto>> ref = new ParameterizedTypeReference<CollectionModel<Gamedto>>() {
		};

		ResponseEntity<CollectionModel<Gamedto>> object = webClient.get().uri("/ongoing").retrieve().toEntity(ref)
				.block();

		CollectionModel<Gamedto> games = object.getBody();

		assertNotNull(games);

		for (Gamedto game : games) {
			assertEquals(game.getResult(), "N");
		}

	}

}
