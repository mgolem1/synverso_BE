package hr.synverso.domain.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class County {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 128)
	private String name;

	@Column(length = 512)
	private String description;

	@OneToMany(mappedBy = "county", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<City> cities;
}
