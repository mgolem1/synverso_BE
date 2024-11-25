package hr.synverso.managers;

import hr.synverso.common.exceptions.AppError;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.domain.address.City;
import hr.synverso.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CityManagerImpl implements CityManager{

	private final CityRepository cityRepository;

	@Override
	public City getCityById(Long id) throws AppException {

		Optional<City> cityOptional = cityRepository.findById(id);

		if (!cityOptional.isPresent()) {
			log.warn("Failed getting city: city {} doesn't exist!", id);
			throw new AppException(AppError.NON_EXISTING_CITY, String.format("Failed getting city: city %s doesn't exist!", id));
		}

		return cityOptional.get();
	}
}
