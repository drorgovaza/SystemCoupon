import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';

import { HttpModule } from '@angular/http';

import { FormsModule } from '@angular/forms';
import 'rxjs/add/operator/map';

import { AppComponent } from './app.component';
import { PurchaseCouponComponent } from './componet/purchase-coupon/purchase-coupon.component';
import { GetAllPurchesedCouponsComponent } from './componet/get-all-purchased-coupon/get-all-purchased-coupon.component';
import { GetAllPurchesCouponsByTypeComponent } from './componet/get-all-coupons-by-type/get-all-coupons-by-type.component';
import { GetCouponsByPriceComponent } from './componet/get-all-coupons-by-price/get-all-coupons-by-price.component';
import { GetAllPurchesCouponsByDateComponent } from './componet/get-all-coupons-by-date/get-all-coupons-by-date.component';


@NgModule({
  declarations: [
    AppComponent,
    PurchaseCouponComponent,
    GetAllPurchesedCouponsComponent,
    GetAllPurchesCouponsByTypeComponent,
    GetCouponsByPriceComponent,
    GetAllPurchesCouponsByDateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path:'purchaseCoupon',
        component:PurchaseCouponComponent
      },
      {
        path:'getAllPurchasedCoupon',
        component:GetAllPurchesedCouponsComponent
      },
      {
        path:'getAllCouponsByType',
        component:GetAllPurchesCouponsByTypeComponent
      },   
      {
        path:'getAllCouponsByPrice',
        component:GetCouponsByPriceComponent
      },  
      {
        path:'getAllCouponsByDate',
        component:GetAllPurchesCouponsByDateComponent
      }    
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }