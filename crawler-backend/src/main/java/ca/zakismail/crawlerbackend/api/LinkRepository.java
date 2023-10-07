package ca.zakismail.crawlerbackend.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<LinkEntity, Long> {
    Optional<List<LinkEntity>> findAllByRootPageId(Long rootPageId);
}
