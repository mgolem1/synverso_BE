package hr.synverso.repositories.criteria;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSearchCriteria implements Serializable {

	private String name;
}
