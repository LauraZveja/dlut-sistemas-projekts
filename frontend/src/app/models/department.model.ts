export class Department {
    idDepartment: number;
    departmentName: string;
    abbreviation: string;
  
    constructor(idDepartment: number, title: string, abbreviation: string) {
      this.idDepartment = idDepartment;
      this.departmentName = title;
      this.abbreviation = abbreviation;
    }
  }
  