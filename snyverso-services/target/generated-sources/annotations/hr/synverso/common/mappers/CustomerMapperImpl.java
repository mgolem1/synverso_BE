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
    date = "2024-11-24T20:47:04+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl extends CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( entity.getId() != null ) {
            customerDTO.setId( String.valueOf( entity.getId() ) );
        }
        customerDTO.setFirstName( entity.getFirstName() );
        customerDTO.setLastName( entity.getLastName() );
        customerDTO.setIdentificationNumber( entity.getIdentificationNumber() );
        if ( entity.getDateOfBirth() != null ) {
            customerDTO.setDateOfBirth( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getDateOfBirth() ) );
        }
        if ( entity.getGender() != null ) {
            customerDTO.setGender( entity.getGender().name() );
        }
        customerDTO.setAddress( addressToAddressDTO( entity.getAddress() ) );

        return customerDTO;
    }

    @Override
    public CustomerBasicInfoDTO customerToCustomerBasicInfoDTO(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerBasicInfoDTO customerBasicInfoDTO = new CustomerBasicInfoDTO();

        if ( entity.getId() != null ) {
            customerBasicInfoDTO.setId( String.valueOf( entity.getId() ) );
        }
        customerBasicInfoDTO.setFirstName( entity.getFirstName() );
        customerBasicInfoDTO.setLastName( entity.getLastName() );
        customerBasicInfoDTO.setIdentificationNumber( entity.getIdentificationNumber() );
        customerBasicInfoDTO.setAddress( addressToAddressDTO( entity.getAddress() ) );

        return customerBasicInfoDTO;
    }

    protected CountyDTO countyToCountyDTO(County county) {
        if ( county == null ) {
            return null;
        }

        CountyDTO countyDTO = new CountyDTO();

        if ( county.getId() != null ) {
            countyDTO.setId( String.valueOf( county.getId() ) );
        }
        countyDTO.setName( county.getName() );
        countyDTO.setDescription( county.getDescription() );

        return countyDTO;
    }

    protected CityDTO cityToCityDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();

        if ( city.getId() != null ) {
            cityDTO.setId( String.valueOf( city.getId() ) );
        }
        cityDTO.setName( city.getName() );
        cityDTO.setDescription( city.getDescription() );
        cityDTO.setCounty( countyToCountyDTO( city.getCounty() ) );

        return cityDTO;
    }

    protected AddressDTO addressToAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        if ( address.getId() != null ) {
            addressDTO.setId( String.valueOf( address.getId() ) );
        }
        addressDTO.setStreetWithHomeNumber( address.getStreetWithHomeNumber() );
        addressDTO.setCity( cityToCityDTO( address.getCity() ) );
        addressDTO.setZip( address.getZip() );
        addressDTO.setCountry( address.getCountry() );

        return addressDTO;
    }
}
