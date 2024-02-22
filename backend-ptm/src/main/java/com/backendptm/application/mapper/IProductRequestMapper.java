package com.backendptm.application.mapper;

import com.backendptm.application.dto.ProductRequestDto;
import com.backendptm.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper para transformar ProductRequest a Product
 */
@Mapper(componentModel = "spring")
public interface IProductRequestMapper {

    /**
     * Crea un Producto con base a un ProductRequestDto
     * @param productRequestDto Dto de un producto de controlador
     * @return Producto creado
     */
    @Mapping(target = "totalValue", ignore = true)
    Product toProduct(ProductRequestDto productRequestDto);
}
