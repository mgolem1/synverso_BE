package hr.synverso.managers;

import hr.synverso.common.exceptions.AppException;
import hr.synverso.domain.address.City;

public interface CityManager {

	City getCityById(Long id) throws AppException;
}
