package io.pivotal.pal.tracker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeEntryJPARepository extends CrudRepository<TimeEntryEntity, Long> {


}
