import { RouterModule, Routes } from '@angular/router';
import { ViewAllEmployeesComponent } from './components/employee/view-all-employees/view-all-employees.component';
import { NgModule } from '@angular/core';
import { UpdateEmployeeComponent } from './components/employee/update-employee/update-employee.component';
import { CreateEmployeeComponent } from './components/employee/create-employee/create-employee.component';
import { HomeComponent } from './components/home/home.component';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import { ViewAllDepartmentsComponent } from './components/department/view-all-departments/view-all-departments.component';
import { UpdateDepartmentComponent } from './components/department/update-department/update-department.component';
import { CreateDepartmentComponent } from './components/department/create-department/create-department.component';


export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent},
    { path: 'update-employee', component: UpdateEmployeeComponent },
    { path: 'create-employee', component: CreateEmployeeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'admin-panel', component: AdminPanelComponent},
    { path: 'departments', component: ViewAllDepartmentsComponent},
    { path: 'update-department', component: UpdateDepartmentComponent},
    { path: 'create-department', component: CreateDepartmentComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }