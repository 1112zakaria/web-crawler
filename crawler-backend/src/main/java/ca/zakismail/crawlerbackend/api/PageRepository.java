package ca.zakismail.crawlerbackend.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends CrudRepository<PageEntity, Long> {
    Optional<PageEntity> findByUrl(String url);
}
