import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { CreateOfferComponent } from './views/create-offer/create-offer.component';
import { PendingOffersComponent } from './views/pending-offers/pending-offers.component';
import { OfferComponent } from './views/offer/offer.component';
import { AuthenticationComponent } from './views/authentication/authentication.component';
import { ApplyingComponent } from './views/applying/applying.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: "/home",
    pathMatch: "full"
  },
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "create-offer",
    component: CreateOfferComponent,
  },
  {
    path: "pending-offers",
    component: PendingOffersComponent
  },
  {
    path: "offer/:uuid",
    component: OfferComponent
  },
  {
    path: "authentication",
    component: AuthenticationComponent
  },
  {
    path: "apply/:uuid",
    component: ApplyingComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
