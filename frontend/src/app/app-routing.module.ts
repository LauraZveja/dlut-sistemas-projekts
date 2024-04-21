import { RouterModule, Routes } from '@angular/router';
import { ViewAllEmployeesComponent } from './components/employee/view-all-employees/view-all-employees.component';
import { NgModule } from '@angular/core';
import { UpdateEmployeeComponent } from './components/employee/update-employee/update-employee.component';
import { CreateEmployeeComponent } from './components/employee/create-employee/create-employee.component';
import { HomeComponent } from './components/home/home.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';


export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent},
    { path: 'update-employee', component: UpdateEmployeeComponent },
    { path: 'create-employee', component: CreateEmployeeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'admin-panel', component: AdminPanelComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }