import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeatureRoutingModule } from './feature-routing.module';
import { PortfolioComponent } from './home/pages/portfolio/portfolio.component';
import { FooterComponent } from './layout/components/footer/footer.component';
import { HeaderComponent } from './layout/components/header/header.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PortfolioComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    FeatureRoutingModule,
    FormsModule,
  ]
})
export class FeatureModule { }
