package com.example.inventorymanagment.Repository;

import com.example.inventorymanagment.Model.InventoryManagementModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryManagementModel, Long> {

    @Query(value = "select * from inventory where supplier =:value", nativeQuery = true)
    List<InventoryManagementModel> findAllBySupplier(@Param("value") String value);

}
