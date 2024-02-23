import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeatureRoutingModule } from './feature-routing.module';
import { PortfolioComponent } from './home/pages/portfolio/portfolio.component';
import { FooterComponent } from './layout/components/footer/footer.component';
import { HeaderComponent } from './layout/components/header/header.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateProductComponent } from './home/pages/create-product/create-product.component';


@NgModule({
  declarations: [
    PortfolioComponent,
    FooterComponent,
    HeaderComponent,
    CreateProductComponent
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    FeatureRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class FeatureModule { }
