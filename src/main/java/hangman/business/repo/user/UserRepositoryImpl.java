package hangman.business.repo.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	EntityManager e;

	public boolean check(String username, String password) {
//		String jpql = "select u from User u";
//
//		Query query = e.createQuery(jpql, User.class);

		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);

		TypedQuery<User> query = e.createQuery(criteriaQuery);

		List<User> list = query.getResultList();

		for (var item : list) {
			if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	public void add(User user) {
		e.persist(user);
	}

	public boolean contains(String username) {
//		String jpql = "select u from User u where username=:username";
//
//		Query query = e.createQuery(jpql, User.class);
//		query.setParameter("username", username);
		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));

		TypedQuery<User> query = e.createQuery(criteriaQuery);

		List<User> list = query.getResultList();

		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public User getUser(String name) {
//		String jpql = "select u from User u where username=:name";
//
//		Query query = e.createQuery(jpql);
//		query.setParameter("name", name);
//		User list = (User) query.getSingleResult();
		CriteriaBuilder criteriaBuilder = e.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("username"), name));

		TypedQuery<User> query = e.createQuery(criteriaQuery);

		User user = query.getSingleResult();

		return user;

	}

	@Transactional
	public void update(User user) {
		e.merge(user);

	}
}
