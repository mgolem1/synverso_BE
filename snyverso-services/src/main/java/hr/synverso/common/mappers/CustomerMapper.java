package hr.synverso.common.mappers;

import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CustomerMapper {

	public abstract CustomerDTO customerToCustomerDTO(Customer entity);

	public abstract CustomerBasicInfoDTO customerToCustomerBasicInfoDTO(Customer entity);
}
