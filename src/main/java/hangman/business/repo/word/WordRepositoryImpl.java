package hangman.business.repo.word;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WordRepositoryImpl implements WordRepository {

	@Autowired
	private EntityManager e;

	public String get(int id) {
		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Word> criteriaQuery = criteriaBuilder.createQuery(Word.class);
		Root<Word> root = criteriaQuery.from(Word.class);
		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		TypedQuery<Word> query = e.createQuery(criteriaQuery);
		Word word = query.getSingleResult();

		return word.getWord();

	}

	public Word getWord(int id) {

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<Word> criteriaQuery = criteriaBuilder.createQuery(Word.class);
		Root<Word> root = criteriaQuery.from(Word.class);
		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		TypedQuery<Word> query = e.createQuery(criteriaQuery);
		Word word = query.getSingleResult();

		return word;

	}

}
