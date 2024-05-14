export class HoursInMonth {
    idHoursInMonth: number;
    year: number;
    month: number;
    hoursInMonth: number;
  
    constructor(idHoursInMonth: number = 0, year: number = 0, month: number = 0, hoursInMonth: number = 0) {
      this.idHoursInMonth = idHoursInMonth;
      this.year = year;
      this.month = month;
      this.hoursInMonth = hoursInMonth;
    }
}