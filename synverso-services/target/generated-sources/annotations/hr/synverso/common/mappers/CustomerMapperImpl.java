package hr.synverso.common.mappers;

import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.common.dto.address.AddressDTO;
import hr.synverso.common.dto.address.CityDTO;
import hr.synverso.common.dto.address.CountyDTO;
import hr.synverso.domain.Customer;
import hr.synverso.domain.address.Address;
import hr.synverso.domain.address.City;
import hr.synverso.domain.address.County;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-25T11:46:33+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl extends CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        if ( entity.getId() != null ) {
            customerDTO.id( String.valueOf( entity.getId() ) );
        }
        customerDTO.firstName( entity.getFirstName() );
        customerDTO.lastName( entity.getLastName() );
        customerDTO.identificationNumber( entity.getIdentificationNumber() );
        if ( entity.getDateOfBirth() != null ) {
            customerDTO.dateOfBirth( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getDateOfBirth() ) );
        }
        if ( entity.getGender() != null ) {
            customerDTO.gender( entity.getGender().name() );
        }
        customerDTO.address( addressToAddressDTO( entity.getAddress() ) );

        return customerDTO.build();
    }

    @Override
    public CustomerBasicInfoDTO customerToCustomerBasicInfoDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerBasicInfoDTO.CustomerBasicInfoDTOBuilder<?, ?> customerBasicInfoDTO = CustomerBasicInfoDTO.builder();

        if ( entity.getId() != null ) {
            customerBasicInfoDTO.id( String.valueOf( entity.getId() ) );
        }
        customerBasicInfoDTO.firstName( entity.getFirstName() );
        customerBasicInfoDTO.lastName( entity.getLastName() );
        customerBasicInfoDTO.identificationNumber( entity.getIdentificationNumber() );
        customerBasicInfoDTO.address( addressToAddressDTO( entity.getAddress() ) );

        return customerBasicInfoDTO.build();
    }

    protected CountyDTO countyToCountyDTO(County county) {
        if ( county == null ) {
            return null;
        }

        CountyDTO.CountyDTOBuilder<?, ?> countyDTO = CountyDTO.builder();

        if ( county.getId() != null ) {
            countyDTO.id( String.valueOf( county.getId() ) );
        }
        countyDTO.name( county.getName() );
        countyDTO.description( county.getDescription() );

        return countyDTO.build();
    }

    protected CityDTO cityToCityDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityDTO.CityDTOBuilder<?, ?> cityDTO = CityDTO.builder();

        if ( city.getId() != null ) {
            cityDTO.id( String.valueOf( city.getId() ) );
        }
        cityDTO.name( city.getName() );
        cityDTO.description( city.getDescription() );
        cityDTO.county( countyToCountyDTO( city.getCounty() ) );

        return cityDTO.build();
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        if ( address.getId() != null ) {
            addressDTO.id( String.valueOf( address.getId() ) );
        }
        addressDTO.streetWithHomeNumber( address.getStreetWithHomeNumber() );
        addressDTO.city( cityToCityDTO( address.getCity() ) );
        addressDTO.zip( address.getZip() );
        addressDTO.country( address.getCountry() );

        return addressDTO.build();
    }
}
