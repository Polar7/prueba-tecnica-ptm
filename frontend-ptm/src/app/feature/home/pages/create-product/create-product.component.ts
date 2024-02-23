import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../../../../core/service/product.service";
import Swal from "sweetalert2";
import {ActivatedRoute, Router} from "@angular/router";

/**
 * Componente para el formulacion de creacion de un producto
 */
@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent {

  /**
   * Formulario reactivo del registro de un producto
   */
  public registerProductForm: FormGroup;

  /**
   * Centinela que indica si esta modificando el producto
   */
  public isModify: boolean;

  constructor(private fb: FormBuilder, private productService: ProductService, private router: Router, private activedRoute: ActivatedRoute) {

    if (this.activedRoute.snapshot.paramMap.get('id') == null) {
      this.registerProductForm = this.fb.group({
        id: ['', Validators.required],
        name: ['', Validators.required],
        description: ['', Validators.required],
        value: ['', Validators.required],
        stock: ['', Validators.required],
      });
      this.isModify = false;
    } else {
      this.registerProductForm = this.fb.group({
        id: [this.activedRoute.snapshot.paramMap.get('id'), Validators.required],
        name: [this.activedRoute.snapshot.paramMap.get('name'), Validators.required],
        description: [this.activedRoute.snapshot.paramMap.get('description'), Validators.required],
        value: [this.activedRoute.snapshot.paramMap.get('value'), Validators.required],
        stock: [this.activedRoute.snapshot.paramMap.get('stock'), Validators.required],
      });
      this.isModify = true;
    }
  }

  /**
   * Registra el producto diligenciado en el formulario
   */
  public registerProduct(): void {
    if (!this.registerProductForm.valid) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Hay errores en el formulario, reviselo por favor.'
      });

      this.registerProductForm.markAllAsTouched();
      return;
    }

    this.productService.registerProduct(this.registerProductForm.value).subscribe({
      next: value => {
        Swal.fire({
          icon: 'success',
          title: 'Registro existoso',
          text: 'Se ha registrado el producto correctamente',
          confirmButtonColor: "#013F4E",
        }).then(x => this.backToHome());
      },
      error: err => {
        Swal.fire({
          icon: 'error',
          title: 'Algo ha ocurrido',
          text: 'Hubo un problema al registrar el producto'
        });
      }
    });
  }

  public modifyProduct(): void {
    if (!this.registerProductForm.valid) {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Hay errores en el formulario, reviselo por favor.'
      });

      this.registerProductForm.markAllAsTouched();
      return;
    }

    this.productService.modifyProduct(this.registerProductForm.value).subscribe({
      next: value => {
        Swal.fire({
          icon: 'success',
          title: 'Modificación existosa',
          text: 'Se ha modificado el producto correctamente',
          confirmButtonColor: "#013F4E",
        }).then(x => this.backToHome());
      },
      error: err => {
        Swal.fire({
          icon: 'error',
          title: 'Algo ha ocurrido',
          text: 'Hubo un problema al modificar el producto'
        });
      }
    });
  }

  /**
   * Retorna a la pantalla principal de la aplicacion
   */
  public backToHome(): void {
    this.router.navigate(["/"])
  }

}
