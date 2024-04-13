import { Department } from "./department.model";

export class Employee {
    idEmployee: number;
    name: string;
    surname: string;
    position: string;
    elected: boolean;
    workContractNoDate: string;
    department: Department;
  
    constructor(
      idEmployee: number,
      name: string,
      surname: string,
      position: string,
      elected: boolean,
      workContractNoDate: string,
      department: Department
    ) {
      this.idEmployee = idEmployee;
      this.name = name;
      this.surname = surname;
      this.position = position;
      this.elected = elected;
      this.workContractNoDate = workContractNoDate;
      this.department = department;
    }
}