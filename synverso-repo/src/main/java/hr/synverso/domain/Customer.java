package hr.synverso.domain;

import hr.synverso.domain.address.Address;
import hr.synverso.domain.codebook.Gender;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 128)
	private String firstName;

	@Column(nullable = false, length = 128)
	private String lastName;

	private String identificationNumber;

	private String username;

	@Column(unique = true, nullable = false)
	private String email;

	private LocalDate dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	private Gender gender;

	@OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
	@LazyToOne(value = LazyToOneOption.NO_PROXY)
	private Address address;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders;
}
