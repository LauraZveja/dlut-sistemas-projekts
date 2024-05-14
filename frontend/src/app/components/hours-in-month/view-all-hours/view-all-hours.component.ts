import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HoursInMonth } from '../../../models/hoursInMonth.model';
import { HoursInMonthService } from '../../../services/hours-in-month/hours-in-month.service';

@Component({
  selector: 'app-view-all-hours',
  templateUrl: './view-all-hours.component.html',
  styleUrls: ['./view-all-hours.component.css']
})
export class ViewAllHoursComponent implements OnInit {
  hoursInMonth: HoursInMonth[] = [];
  monthNames: string[] = [
    '', 'janvāris', 'februāris', 'marts', 'aprīlis', 'maijs', 'jūnijs', 
    'jūlijs', 'augusts', 'septembris', 'oktobris', 'novembris', 'decembris'
  ];
  inputYear: number = new Date().getFullYear();
  inputYearForAllHours: number = new Date().getFullYear() + 1;

  constructor(
    private hoursInMonthService: HoursInMonthService, 
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const yearParam = params.get('year');
      const currentYear = new Date().getFullYear();
      const year = yearParam ? +yearParam : null;
      if (year && year != currentYear) {
        this.inputYear = year;
        this.getHours(year);
      } else {
        this.getHoursThisYear();
      }
    });
  }

  getMonthName(month: number): string {
    return this.monthNames[month];
  }

  private getHours(year: number): void {
    this.hoursInMonthService.getAllHoursInYear(year).subscribe(data => {
      this.hoursInMonth = data;
    });
  }

  onSubmit(): void {
    if (this.inputYear) {
      this.router.navigate(['/hours-in-year', this.inputYear]);
    }
  }

  private getHoursThisYear() {
    this.hoursInMonthService.getAllHoursInThisYear().subscribe(data => {
      this.hoursInMonth = data;
    });
  }

  navigateToUpdate(hours: HoursInMonth): void {
    this.router.navigate(['/update-hours'], { state: { hours } });
  }

  navigateToCreate(): void {
    this.router.navigate(['/create-hours'], { state: { year: this.inputYear } });
  }

  deleteHours(hours: HoursInMonth): void {
    this.hoursInMonthService.deleteHoursInMonth(hours).subscribe({
      next: () => {
        this.getHours(this.inputYear);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  confirmDelete(hours: HoursInMonth): void {
    if (confirm('Vai tiešām vēlaties dzēst šo ierakstu?')) {
      this.deleteHours(hours);
    }
  }


  onEnterAllHours(): void {
    if (this.inputYearForAllHours) {
        this.router.navigate(['/enter-all-hours'], { state: { year: this.inputYearForAllHours } });
    }
}

}
