package hr.synverso.common.dto;

import hr.synverso.common.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBasicInfoDTO implements Serializable {

	private String id;

	private String firstName;

	private String lastName;

	private String identificationNumber;

	private AddressDTO address;
}
