import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EmpresaEditComponent } from './page/empresa-edit/empresa-edit.component';
import { EmpresaListComponent } from './page/empresa-list/empresa-list.component';
import { HomeComponent } from './page/home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'empresa', component: EmpresaListComponent },
  { path: 'empresa/new', component: EmpresaEditComponent },
  { path: 'empresa/edit/:id', component: EmpresaEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
