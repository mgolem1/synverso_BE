package hr.synverso.common.dto;

import hr.synverso.common.dto.address.AddressDTO;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
