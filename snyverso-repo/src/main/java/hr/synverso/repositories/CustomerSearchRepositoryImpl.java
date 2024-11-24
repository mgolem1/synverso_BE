package hr.synverso.repositories;

import hr.synverso.domain.Customer;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import hr.synverso.repositories.util.PathUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerSearchRepositoryImpl implements CustomerSearchRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Customer> findCustomersBySearchCriteria(CustomerSearchCriteria criteria, Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Long> customerIdQuery = criteriaBuilder.createQuery(Long.class);
		Root<Customer> root = customerIdQuery.from(Customer.class);

		customerIdQuery.select(root.get("id"));
		customerIdQuery.where(getWhereClause(root, customerIdQuery, criteriaBuilder, criteria));

		customerIdQuery.orderBy(getCustomerOrders(criteriaBuilder, root, pageable));
		customerIdQuery.groupBy(root.get("id"));

		List<Long> userIds = entityManager.createQuery(customerIdQuery)
				.setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();

		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

		criteriaQuery.select(root).distinct(true);

		criteriaQuery.where(criteriaBuilder.in(root.get("id")).value(userIds));
		criteriaQuery.orderBy(getCustomerOrders(criteriaBuilder, root, pageable));

		List<Customer> customers = entityManager.createQuery(criteriaQuery).getResultList();

		CriteriaQuery<Long> userCountQuery = criteriaBuilder.createQuery(Long.class);
		Root<Customer> countRoot = userCountQuery.from(Customer.class);

		userCountQuery.select(criteriaBuilder.countDistinct(countRoot));
		userCountQuery.where(getWhereClause(countRoot, criteriaQuery, criteriaBuilder, criteria));

		Long count = entityManager.createQuery(userCountQuery).getSingleResult();

		return new PageImpl<>(customers, pageable, count);
	}

	private List<Order> getCustomerOrders(CriteriaBuilder criteriaBuilder, Root<Customer> root, Pageable pageable) {

		List<Order> orders = new ArrayList<>();

		for (Sort.Order o : pageable.getSort()) {

			if (o.isAscending()) {
				orders.add(criteriaBuilder.asc(PathUtil.getOrderPath(o.getProperty(), root)));
			} else {
				orders.add(criteriaBuilder.desc(PathUtil.getOrderPath(o.getProperty(), root)));
			}
		}

		return orders;
	}


	private Predicate getWhereClause(Root<Customer> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder, CustomerSearchCriteria criteria) {

		List<Predicate> restrictions = new ArrayList<>();

		String name = criteria.getName();

		if (name != null && !name.isEmpty()) {
			restrictions.add(
					criteriaBuilder.or(
							criteriaBuilder.like(
									criteriaBuilder.concat(
											criteriaBuilder.concat(criteriaBuilder.lower(root.get("firstName")), " "),
											criteriaBuilder.lower(root.get("lastName"))), "%" + name.toLowerCase() + "%"
							),
							criteriaBuilder.like(
									criteriaBuilder.concat(
											criteriaBuilder.concat(criteriaBuilder.lower(root.get("lastName")), " "),
											criteriaBuilder.lower(root.get("firstName"))), "%" + name.toLowerCase() + "%"
							),
							criteriaBuilder.like(
									criteriaBuilder.lower(root.get("firstName")), "%" + name.toLowerCase() + "%"
							),
							criteriaBuilder.like(
									criteriaBuilder.lower(root.get("lastName")), "%" + name.toLowerCase() + "%"
							)
					)
			);
		}

		return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
	}
}
