package hr.synverso.repositories;

import hr.synverso.domain.Customer;
import hr.synverso.domain.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
