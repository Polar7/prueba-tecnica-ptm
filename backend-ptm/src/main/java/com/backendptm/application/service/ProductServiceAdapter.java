package com.backendptm.application.service;

import com.backendptm.domain.model.Product;
import com.backendptm.domain.repository.IProductRepositoryPort;
import com.backendptm.domain.service.IProductServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador con las implementaciones del repositorio de Producto
 */
@Service
public class ProductServiceAdapter implements IProductServicePort {

    /**
     * Puerto del repositorio de Producto
     */
    private final IProductRepositoryPort repositoryPort;

    /**
     * Construye el adaptador del servicio de Producto
     * @param repositoryPort Puerto del repository de producto
     */
    public ProductServiceAdapter(IProductRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    /**
     * Obtiene todos los productos
     * @return Lista con todos los productos existentes
     */
    @Override
    public List<Product> getAll() {
        return repositoryPort.getAll();
    }

    /**
     * Devuelve un producto dado su id
     * @param idProduct Id del producto a buscar
     * @return Optional del producto encontrado
     */
    @Override
    public Optional<Product> getProductById(Integer idProduct) {
        return repositoryPort.getProductById(idProduct);
    }

    /**
     * Guarda un Producto
     * @param product Producto a guardar
     * @return Producto guardado
     */
    @Override
    public Product save(Product product) {
        return repositoryPort.save(product);
    }

    /**
     * Modifica un Producto
     * @param product Producto a modificar
     * @return Producto modificado
     */
    @Override
    public Optional<Product> update(Product product) {
        if (repositoryPort.getProductById(product.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(save(product));
    }

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    @Override
    public boolean delete(Integer idProduct) {
        if (repositoryPort.getProductById(idProduct).isEmpty()) {
            return false;
        }

        repositoryPort.delete(idProduct);
        return true;
    }
}
