package hangman.business.repo.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	WordRepositoryImpl wordRepo;

	public String getWord(int num) {
		return wordRepo.get(num);
	}

	public Word setWord(int num) {
		Word word = new Word();
		word = wordRepo.getWord(num);

		return word;

	}

}
