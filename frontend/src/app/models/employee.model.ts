export class Employee {
    idEmployee: number;
    name: string;
    surname: string;
    position: string;
    elected: boolean;
    workContractNoDate: string;
    departmentName: string;
  
    constructor(
      idEmployee: number = 0,
      name: string = '',
      surname: string = '',
      position: string = '',
      elected: boolean = false,
      workContractNoDate: string = '',
      departmentName: string = ''
    ) {
      this.idEmployee = idEmployee;
      this.name = name;
      this.surname = surname;
      this.position = position;
      this.elected = elected;
      this.workContractNoDate = workContractNoDate;
      this.departmentName = departmentName;
    }

}