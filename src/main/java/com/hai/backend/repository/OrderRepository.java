package com.hai.backend.repository;

import com.hai.backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserEntityId(int id);

    OrderEntity findById(int id);

    @Query(
            value = "SELECT  concat(MONTH(create_date),'/',YEAR(create_date)) as 'month', sum(totalprice) as 'totalprice' FROM dothethao.user_order group by month order by month desc",
            nativeQuery = true
    )
    List<Object> analysticOrder();
}
