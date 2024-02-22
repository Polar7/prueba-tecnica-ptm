package com.backendptm.infrastructure.persistance.repository;

import com.backendptm.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz con los metodos de JpaRepository para ProductEntity
 */
public interface IProductCrudRepository extends JpaRepository<ProductEntity, Integer> {
}
