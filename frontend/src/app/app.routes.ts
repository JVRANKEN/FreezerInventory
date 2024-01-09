import {RouterModule, Routes} from '@angular/router';
import {ListOverviewComponent} from "../list-overview/list-overview.component";
import {ListDetailComponent} from "../list-detail/list-detail.component";
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  // puth dashboard back to appcomponent??
  { path: 'dashboard', component: ListOverviewComponent },
  { path: 'overview', component: ListOverviewComponent },
  { path: 'detail', component: ListDetailComponent }

  // { path: 'detail/:id', component: ListDetailComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutes {}
