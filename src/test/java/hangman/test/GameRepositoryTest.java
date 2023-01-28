package hangman.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import hangman.business.repo.Game;
import hangman.business.repo.GameRepository;
import hangman.business.repo.GameRepositoryImpl;



class GameRepositoryTest {
	
	Game Game;
	
	String[] list;

	HashMap<UUID,Game> gameRepository=new HashMap<UUID,Game>();
	private GameRepository underTest;
	
	@BeforeEach
	void setUp() {
	list=new String[] {"Abyss, Bubbly, Buzz, Buff, Cozy, Fluff, Fluffy, Fizz, Fizzy, Fox, Jinx, Lucky, Puzzle, Foxglove, Abruptly, Voyeurism, Pneumonia, Jiujitsu, Espionage, Witchcraft, Razzmatazz, Zigzagging, Buckaroo, Iatrogenic, Jawbreaker, Voodoo, Jazz_singer_drops_beats, Buzzing_around_the_beekeeper, Awkward_klutzy_numbskull, Croquet_players_fix_games, Throwing_gnarly_punches, Absurd_wizard_mystifies, Jiujitsu_masters_train, Wimpy_geek_panics, Twelve_foxes_hunt, A_quiet_jinx_sulks, Nine_tailed_demon_fox, The_great__wall_of_china, Sage_of_six_paths"};
	 Game=new Game(0,0,6,0,1,"","",list,"N");
	}
	
	
	@Test
	void testUpdate() {
		underTest=new GameRepositoryImpl(gameRepository);
		//given 

		Game.setWord("hi");
		Game game1=new Game(0,0,6,0,1,"","",list,"N");
		game1.setWord("hello");
		UUID id=UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");
		underTest.add(id,Game);
		Game check1=underTest.getGameById(id);
		//when
		underTest.update(id,game1);
		Game check2=underTest.getGameById(id);
		//then
		assertNotEquals(check1,check2);
	}
	
	@Test
	void testGetGameById() {
		underTest=new GameRepositoryImpl(gameRepository);
		//given 

		UUID id=UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");
		underTest.add(id,Game);
		//when
		Game check=underTest.getGameById(id);
		//then
		assertEquals(check,Game);
	}
	
	@Test
	void testIsEmptyTrue() {
		//given
		underTest=new GameRepositoryImpl(gameRepository);
		//when
	    boolean cond=underTest.isEmpty();
	 
	    //then
	    assertEquals(cond,true);
	}

	@Test
	void testAdd() {
		//given
		underTest=new GameRepositoryImpl(gameRepository);

		UUID id=UUID.fromString("668c1752-ae7a-4675-b18f-e6204d359078");
		underTest.add(id,Game);
		//when
	    boolean cond=underTest.isEmpty();
	 
	    //then
	    assertEquals(cond,false);
	}
}
