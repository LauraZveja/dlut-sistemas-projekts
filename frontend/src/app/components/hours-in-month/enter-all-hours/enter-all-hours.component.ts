import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HoursInMonth } from '../../../models/hoursInMonth.model';
import { HoursInMonthService } from '../../../services/hours-in-month/hours-in-month.service';

@Component({
  selector: 'app-enter-all-hours',
  templateUrl: './enter-all-hours.component.html',
  styleUrls: ['./enter-all-hours.component.css']
})
export class EnterAllHoursComponent implements OnInit {
  hoursInYear: HoursInMonth[] = [];
  monthNames: string[] = [
    'Janvāris', 'Februāris', 'Marts', 'Aprīlis', 'Maijs', 'Jūnijs', 
    'Jūlijs', 'Augusts', 'Septembris', 'Oktobris', 'Novembris', 'Decembris'
  ];
  inputYear: number;

  constructor(
    private hoursInMonthService: HoursInMonthService,
    private router: Router,
    private location: Location
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.inputYear = navigation.extras.state['year'];
      this.initializeHoursInYear(this.inputYear);
    } else {
      this.inputYear = new Date().getFullYear();
      this.initializeHoursInYear(this.inputYear);
    }
  }

  ngOnInit(): void { }

  initializeHoursInYear(year: number): void {
    for (let i = 1; i <= 12; i++) {
      this.hoursInYear.push(new HoursInMonth(year, i, 0));
    }
  }

  onSubmit(): void {
    if (this.hoursInYear) {
      this.hoursInMonthService.insertHoursInYear(this.hoursInYear).subscribe({
        next: () => {
          this.router.navigate(['/hours-in-year', this.inputYear]);
        },
        error: (error) => {
          console.error('Dati netika pievienoti:', error.message);
          alert('Dati netika pievienoti, lūdzu, mēģiniet vēlreiz.');
        }
      });
    } else {
      alert('Lūdzu, aizpildiet visus nepieciešamos laukus.');
    }
  }

  goBack(): void {
    this.location.back();
  }
}
