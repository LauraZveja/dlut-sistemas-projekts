import { RouterModule, Routes } from '@angular/router';
import { ViewAllEmployeesComponent } from './components/view-all-employees/view-all-employees.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    { path: '', redirectTo: 'employees', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }