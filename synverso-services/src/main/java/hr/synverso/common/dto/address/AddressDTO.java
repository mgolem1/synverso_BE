package hr.synverso.common.dto.address;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO implements Serializable {

	private String id;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 128)
	private String streetWithHomeNumber;

	@NotNull
	private CityDTO city;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 128)
	private String zip;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 128)
	private String country;


}
