package hr.synverso.common.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CountyDTO implements Serializable {

	private String id;

	@Size(max = 128)
	private String name;

	@Size(max = 512)
	private String description;
}
