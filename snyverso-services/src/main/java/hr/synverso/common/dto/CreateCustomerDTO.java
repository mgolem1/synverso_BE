package hr.synverso.common.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
