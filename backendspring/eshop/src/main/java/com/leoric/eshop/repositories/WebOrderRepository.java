package com.leoric.eshop.repositories;

import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.models.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
    List<WebOrder> findByUser(LocalUser user);
}
