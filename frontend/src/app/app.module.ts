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
import { RouterModule } from "@angular/router";
import { ViewAllDepartmentsComponent } from "./components/department/view-all-departments/view-all-departments.component";
import { UpdateDepartmentComponent } from "./components/department/update-department/update-department.component";


@NgModule({
  declarations: [
    AppComponent,
    ViewAllEmployeesComponent,
    UpdateEmployeeComponent,
    CreateEmployeeComponent,
    AdminPanelComponent,
    ViewAllDepartmentsComponent,
    UpdateDepartmentComponent
    
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

