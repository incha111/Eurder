package com.eurder.eurder.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemGroupRepository extends JpaRepository<ItemGroup,Integer> {
    List<ItemGroup> findItemGroupsByOrderId(int orderId);
}
