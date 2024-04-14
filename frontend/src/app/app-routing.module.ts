import { RouterModule, Routes } from '@angular/router';
import { ViewAllEmployeesComponent } from './components/employee/view-all-employees/view-all-employees.component';
import { NgModule } from '@angular/core';
import { UpdateEmployeeComponent } from './components/employee/update-employee/update-employee.component';
import { CreateEmployeeComponent } from './components/employee/create-employee/create-employee.component';


export const routes: Routes = [
    { path: '', redirectTo: 'employees', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent},
    { path: 'update-employee', component: UpdateEmployeeComponent },
    { path: 'create-employee', component: CreateEmployeeComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }