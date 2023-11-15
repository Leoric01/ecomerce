package com.leoric.eshop.services;

import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.models.WebOrder;
import com.leoric.eshop.repositories.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final WebOrderRepository orderRepository;
  @Autowired
  public OrderServiceImpl(WebOrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }
  @Override
  public List<WebOrder> getOrders(LocalUser user){
    return orderRepository.findByUser(user);
  }
}
