package acme.features.anonymous.shout;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
    @Query("select s from Shout s")
    Collection<Shout> findMany();
    
    @Query("select s from Shout s where s.moment >= ?1 order by s.moment desc")
    Collection<Shout> findManyRecent(Date date);
}



