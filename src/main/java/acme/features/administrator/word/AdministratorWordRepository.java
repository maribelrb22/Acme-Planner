package acme.features.administrator.word;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.entities.spam.Word;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorWordRepository extends AbstractRepository{

	@Query("select w from Word w")
    Collection<Word> findAllWords();
	
	@Query("select w from Word w where w.id = ?1")
	Word findOneWordById(int id);
	
	@Query("select s from Spam s")
	Spam findSpam();
	
}
