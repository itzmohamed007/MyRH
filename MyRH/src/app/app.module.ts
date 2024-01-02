import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './views/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CreateOfferComponent } from './views/create-offer/create-offer.component';
import { PendingOffersComponent } from './views/pending-offers/pending-offers.component';
import { OfferComponent } from './views/offer/offer.component';
import { AuthenticationComponent } from './views/authentication/authentication.component';
import { ApplyingComponent } from './views/applying/applying.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    CreateOfferComponent,
    PendingOffersComponent,
    OfferComponent,
    AuthenticationComponent,
    ApplyingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
