/**
 * Dto de un Producto
 */
export interface ProductDto {

  /**
   * Id del producto
   */
  id: number;

  /**
   * Nombre del producto
   */
  name: string;

  /**
   * Descripcion del producto
   */
  description: string;

  /**
   * Precio del producto
   */
  value: number;

  /**
   * Unidades en existencia del producto
   */
  stock: number;

  /**
   * Valor total del inventario del producto
   */
  totalValue: number;
}
