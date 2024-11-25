package hr.synverso.domain.address;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 128)
	private String streetWithHomeNumber;

	@Column(length = 128)
	private String zip;

	@Column(length = 128)
	private String country;

	@ManyToOne(fetch = FetchType.LAZY)
	private City city;
}
