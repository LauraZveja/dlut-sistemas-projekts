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
import { ViewAllHoursComponent } from './components/hours-in-month/view-all-hours/view-all-hours.component';
import { UpdateHoursComponent } from './components/hours-in-month/update-hours/update-hours.component';
import { CreateHoursComponent } from './components/hours-in-month/create-hours/create-hours.component';
import { EnterAllHoursComponent } from './components/hours-in-month/enter-all-hours/enter-all-hours.component';


export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'employees', component: ViewAllEmployeesComponent},
    { path: 'update-employee', component: UpdateEmployeeComponent },
    { path: 'create-employee', component: CreateEmployeeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'admin-panel', component: AdminPanelComponent},
    { path: 'departments', component: ViewAllDepartmentsComponent},
    { path: 'update-department', component: UpdateDepartmentComponent},
    { path: 'create-department', component: CreateDepartmentComponent},
    { path: 'hours-in-this-year', component: ViewAllHoursComponent},
    { path: 'update-hours', component: UpdateHoursComponent},
    { path: 'create-hours', component: CreateHoursComponent},
    { path: 'hours-in-year/:year', component: ViewAllHoursComponent },
    { path: 'enter-all-hours', component: EnterAllHoursComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }