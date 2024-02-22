package com.backendptm.domain.service;

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
    Product save(Product product);

    /**
     * Modifica un Producto
     * @param product Producto a modificar
     * @return Producto modificado
     */
    Optional<Product> update(Product product);

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    boolean delete(Integer idProduct);
}
