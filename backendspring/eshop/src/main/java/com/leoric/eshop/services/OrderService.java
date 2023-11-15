package com.leoric.eshop.services;

import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.models.WebOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<WebOrder> getOrders(LocalUser user);
}
