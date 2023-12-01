package org.codewithrishit.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.codewithrishit.dto.OrderLineItemsDto;
import org.codewithrishit.dto.OrderRequest;
import org.codewithrishit.model.Order;
import org.codewithrishit.model.OrderLineItems;
import org.codewithrishit.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();

		order.setOrderLineItemList(orderLineItems);

		orderRepository.save(order);
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItems.getSkuCode());

		return orderLineItems;
	}
}
