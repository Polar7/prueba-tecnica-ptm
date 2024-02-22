/**
 * Dto de una Combinacion
 */
export interface CombinationDto {

  /**
   * Nombres de los productos
   */
  names: Array<string>;

  /**
   * Suma de los valores de los productos que estan en la lista names
   */
  total: number;
}
