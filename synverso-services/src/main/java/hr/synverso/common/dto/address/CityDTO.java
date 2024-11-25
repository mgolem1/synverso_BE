package hr.synverso.common.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CityDTO implements Serializable {

	private String id;

	@Size(max = 128)
	private String name;

	@Size(max = 512)
	private String description;

	@NotNull
	private CountyDTO county;
}
