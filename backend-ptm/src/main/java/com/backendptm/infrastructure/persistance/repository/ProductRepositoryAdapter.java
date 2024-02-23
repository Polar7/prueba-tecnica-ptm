package com.backendptm.infrastructure.persistance.repository;

import com.backendptm.domain.model.Product;
import com.backendptm.domain.repository.IProductRepositoryPort;
import com.backendptm.infrastructure.persistance.mapper.IProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador con las implementaciones del repositorio de Producto
 */
@Repository
public class ProductRepositoryAdapter implements IProductRepositoryPort {

    /**
     * Crud del repositorio de producto
     */
    private final IProductCrudRepository crudRepository;

    /**
     * Mapper de producto
     */
    private final IProductMapper mapper;

    /**
     * Construye el adaptador del repositorio de Producto
     * @param crudRepository CrudRepository de producto
     * @param mapper Mapper de producto
     */
    public ProductRepositoryAdapter(IProductCrudRepository crudRepository, IProductMapper mapper) {
        this.crudRepository = crudRepository;
        this.mapper = mapper;
    }

    /**
     * Obtiene todos los productos
     * @return Lista con todos los productos existentes
     */
    @Override
    public List<Product> getAll() {
        return mapper.toProductList(crudRepository.findAll());
    }

    /**
     * Devuelve un producto dado su id
     * @param idProduct Id del producto a buscar
     * @return Optional del producto encontrado
     */
    @Override
    public Optional<Product> getProductById(Integer idProduct) {
        return crudRepository.findById(idProduct)
                .map(mapper::toProduct);
    }

    /**
     * Guarda o modifica un Producto
     * @param product Producto a guardar/modificar
     * @return Producto guardado/modificado
     */
    @Override
    public Product save(Product product) {
        return mapper.toProduct(
                crudRepository.save(mapper.toProductEntity(product)));
    }

    /**
     * Elimina un producto dado su id
     * @param idProduct Id del producto a eliminar
     */
    @Override
    public void delete(Integer idProduct) {
        crudRepository.deleteById(idProduct);
    }


}
