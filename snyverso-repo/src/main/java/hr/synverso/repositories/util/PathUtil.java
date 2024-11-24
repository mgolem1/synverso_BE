package hr.synverso.repositories.util;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class PathUtil {

	public static <Y> Path<Y> getOrderPath(String orderProperty, Root root) {
		Path orderPath = null;

		if (orderProperty.contains("_")) {
			String[] properties = orderProperty.split("_");
			for (int i = 0; i < properties.length; i++) {
				String property = properties[i];

				if (i == 0) {
					orderPath = root.get(property);
				} else {
					orderPath = orderPath.get(property);
				}

			}
		} else {
			orderPath = root.get(orderProperty);
		}
		return orderPath;
	}

	public static <Y> Path<Y> getJoinOrderPath(String orderProperty, Join join) {
		Path orderPath = null;

		if (orderProperty.contains("_")) {
			String[] properties = orderProperty.split("_");
			for (int i = 0; i < properties.length; i++) {
				String property = properties[i];

				if (i == 0) {
					orderPath = join.get(property);
				} else {
					orderPath = orderPath.get(property);
				}

			}
		} else {
			orderPath = join.get(orderProperty);
		}
		return orderPath;
	}
}

