package acme.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamRepository extends AbstractRepository {

    @Query("select s from Spam s")
    Spam findOne();
}
