import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HoursInMonthService } from '../../../services/hours-in-month/hours-in-month.service';
import { HoursInMonth } from '../../../models/hoursInMonth.model';

@Component({
  selector: 'app-create-hours',
  templateUrl: './create-hours.component.html',
  styleUrls: ['./create-hours.component.css']
})
export class CreateHoursComponent {
  hours: HoursInMonth = new HoursInMonth();
  monthNames: string[] = [
    '', 'janvāris', 'februāris', 'marts', 'aprīlis', 'maijs', 'jūnijs', 
    'jūlijs', 'augusts', 'septembris', 'oktobris', 'novembris', 'decembris'
  ];

  constructor(
    private hoursInMonthService: HoursInMonthService,
    private router: Router,
    private location: Location
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.hours.year = navigation.extras.state['year'] || new Date().getFullYear();
    } else {
      this.hours.year = new Date().getFullYear();
    }
  }

  onSubmit(): void {
    if (this.hours) {
      this.hoursInMonthService.insertHoursInMonth(this.hours).subscribe({
        next: () => {
          this.router.navigate(['/hours-in-year', this.hours.year]);
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

  getMonthName(month: number): string {
    return this.monthNames[month];
  }
}
