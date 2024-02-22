package com.backendptm.domain.repository;

import com.backendptm.domain.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Puerto con las operaciones que debe tener el repositorio de Producto
 */
public interface IProductRepositoryPort {

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
     * Guarda o modifica un Producto
     * @param product Producto a guardar/modificar
     * @return Producto guardado/modificado
     */
    Product save(Product product);

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    void delete(Integer idProduct);
}
