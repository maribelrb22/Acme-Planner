package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.entities.spam.WordClass;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWordRepository extends AbstractRepository{

	@Query("select w from WordClass w")
    Collection<WordClass> findAllWords();
	
	@Query("select w from WordClass w where w.id = ?1")
	WordClass findOneWordById(int id);
	
	@Query("select s from Spam s")
	Spam findSpam();
	
}
