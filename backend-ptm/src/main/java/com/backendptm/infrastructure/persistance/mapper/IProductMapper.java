package com.backendptm.infrastructure.persistance.mapper;

import com.backendptm.domain.model.Product;
import com.backendptm.infrastructure.persistance.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper para transformar modelos y entidades de Producto
 */
@Mapper(componentModel = "spring")
public interface IProductMapper {

    /**
     * Crea un Producto con base a un ProductEntity
     * @param productEntity Entidad Producto
     * @return Producto creado
     */
    @Mapping(target = "totalValue", expression = "java(calculateTotalValue(productEntity))")
    Product toProduct(ProductEntity productEntity);

    /**
     * Crea un Producto con base a un ProductEntity
     * @param product Model de Producto
     * @return Entidad de Producto creada
     */
    ProductEntity toProductEntity(Product product);

    /**
     * Crea una lista de productos con base a una lista de ProductEntities
     * @param productEntityList Lista de ProductEntities
     * @return Lista de productos creada
     */
    List<Product> toProductList(List<ProductEntity> productEntityList);

    /**
     * Retorna el valor total de inventario de un Producto
     * @param productEntity Entidad producto
     * @return Valor calculado
     */
    default double calculateTotalValue(ProductEntity productEntity) {
        return productEntity.getValue() * productEntity.getStock();
    }
}
