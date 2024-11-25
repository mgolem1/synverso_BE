package hr.synverso.common.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDTO extends CustomerDTO {

	@NotNull
	@NotEmpty
	@NotBlank
	private String username;

	@NotNull
	@NotEmpty
	@NotBlank
	private String email;
}
