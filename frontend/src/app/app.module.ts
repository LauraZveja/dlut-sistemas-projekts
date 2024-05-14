import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserModule} from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ViewAllEmployeesComponent } from "./components/employee/view-all-employees/view-all-employees.component";
import { EmployeeService } from "./services/employee/employee.service";
import { UpdateEmployeeComponent } from "./components/employee/update-employee/update-employee.component";
import { CreateEmployeeComponent } from "./components/employee/create-employee/create-employee.component";
import { AdminPanelComponent } from "./components/admin-panel/admin-panel.component";
import { CreateDepartmentComponent } from "./components/department/create-department/create-department.component";
import { RouterModule } from "@angular/router";
import { ViewAllDepartmentsComponent } from "./components/department/view-all-departments/view-all-departments.component";
import { UpdateDepartmentComponent } from "./components/department/update-department/update-department.component";
import { ViewAllHoursComponent } from "./components/hours-in-month/view-all-hours/view-all-hours.component";
import { UpdateHoursComponent } from "./components/hours-in-month/update-hours/update-hours.component";
import { CreateHoursComponent } from "./components/hours-in-month/create-hours/create-hours.component";
import { EnterAllHoursComponent } from "./components/hours-in-month/enter-all-hours/enter-all-hours.component";


@NgModule({
  declarations: [
    AppComponent,
    ViewAllEmployeesComponent,
    UpdateEmployeeComponent,
    CreateEmployeeComponent,
    AdminPanelComponent,
    ViewAllDepartmentsComponent,
    UpdateDepartmentComponent,
    CreateDepartmentComponent,
    ViewAllHoursComponent,
    UpdateHoursComponent,
    CreateHoursComponent,
    EnterAllHoursComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule {
  
}

