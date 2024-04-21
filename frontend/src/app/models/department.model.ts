export class Department {
    idDepartment: number;
    title: string;
    abbreviation: string;
  
    constructor(
      idDepartment: number = 0,
      title: string = '',
      abbreviation: string = ''
    ) {
      this.idDepartment = idDepartment;
      this.title = title;
      this.abbreviation = abbreviation;
      
    }
  }
  