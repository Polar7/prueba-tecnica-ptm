import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PortfolioComponent} from "./home/pages/portfolio/portfolio.component";
import {CreateProductComponent} from "./home/pages/create-product/create-product.component";

const routes: Routes = [
  {
    path: "",
    component: PortfolioComponent
  },
  {
    path: "crear-producto",
    component: CreateProductComponent
  },
  {
    path: "crear-producto/:id",
    component: CreateProductComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeatureRoutingModule { }
