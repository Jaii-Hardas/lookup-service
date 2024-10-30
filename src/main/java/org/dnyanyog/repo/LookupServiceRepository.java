package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface LookupServiceRepository extends JpaRepository<Lookup, Long> {

  List<Lookup> findByTenant(String tenant);
}
