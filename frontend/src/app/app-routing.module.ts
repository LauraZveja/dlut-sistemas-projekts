import { RouterModule, Routes } from '@angular/router';
import { ViewAllEmployeesComponent } from './components/employee/view-all-employees/view-all-employees.component';
import { NgModule } from '@angular/core';
import { UpdateEmployeeComponent } from './components/employee/update-employee/update-employee.component';  // Make sure the path matches your project structure


export const routes: Routes = [
    { path: '', redirectTo: 'employees', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent},
    { path: 'update-employee', component: UpdateEmployeeComponent } 
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }