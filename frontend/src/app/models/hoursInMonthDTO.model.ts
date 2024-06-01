export class HoursInMonthDTO {
    year: number;
    month: number;
    hoursInMonth: number;
  
    constructor(year: number = 0, month: number = 0, hoursInMonth: number = 0) {
      this.year = year;
      this.month = month;
      this.hoursInMonth = hoursInMonth;
    }

}