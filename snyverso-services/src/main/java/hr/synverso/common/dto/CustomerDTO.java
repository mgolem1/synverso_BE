package hr.synverso.common.dto;

import hr.synverso.common.dto.address.AddressDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO implements Serializable {

	private String id;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 128)
	private String firstName;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 128)
	private String lastName;

	private String identificationNumber;

	private String dateOfBirth;

	@Size(max = 1)
	private String gender;

	private AddressDTO address;
}
