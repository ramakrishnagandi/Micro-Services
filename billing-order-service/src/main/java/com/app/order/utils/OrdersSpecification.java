package com.app.order.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.app.order.dto.OrderFilterDTO;
import com.app.order.entity.OrderEntity;

import jakarta.persistence.criteria.Predicate;

public class OrdersSpecification {
	public static Specification<OrderEntity> getOrdersByFilter(OrderFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {
        	List<Predicate> predicates = new ArrayList<>(); // This will hold all predicates (conditions)
            
            // Filter by order date range
            if (filter.getStartDate() != null && filter.getEndDate() != null) {
            	predicates.add(criteriaBuilder.between(root.get("createdAt"), 
            			filter.getStartDate(), filter.getEndDate()));
            }
            
            
            if (filter.getOrderNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("orderId"), filter.getOrderNumber()));
            }
            
            if(filter.getCustomerId() != null) {
            	predicates.add(criteriaBuilder.equal(root.get("customerId"), filter.getCustomerId()));
            }
            

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
