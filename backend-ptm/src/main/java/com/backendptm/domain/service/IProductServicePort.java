package com.backendptm.domain.service;

import com.backendptm.application.dto.ProductRequestDto;
import com.backendptm.domain.model.Combination;
import com.backendptm.domain.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Puerto con las operaciones que debe tener el servicio de Producto
 */
public interface IProductServicePort {

    /**
     * Obtiene todos los productos
     * @return Lista con todos los productos existentes
     */
    List<Product> getAll();

    /**
     * Devuelve un producto dado su id
     * @param idProduct Id del producto a buscar
     * @return Optional del producto encontrado
     */
    Optional<Product> getProductById(Integer idProduct);

    /**
     * Guarda un Producto
     * @param product Producto a guardar
     * @return Producto guardado
     */
    Product save(ProductRequestDto product);

    /**
     * Modifica un Producto
     * @param product Producto a modificar
     * @return Producto modificado
     */
    Optional<Product> update(ProductRequestDto product);

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    boolean delete(Integer idProduct);

    /**
     * Retorna una lista de combinaciones sin repeticion de nombres de productos donde
     * la suma de sus precios no es superior al valor que llega por parametro
     * @param valueFilter Valor que sirve de filtro
     * @return Listado de nombres de productos que cumplen con el filtro, ordenados descendentemente por el precio
     */
    List<Combination> getCombinationProducts(Double valueFilter);
}
