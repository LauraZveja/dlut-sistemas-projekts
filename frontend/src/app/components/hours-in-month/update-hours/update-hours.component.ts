import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { HoursInMonthService } from '../../../services/hours-in-month/hours-in-month.service';
import { HoursInMonth } from '../../../models/hoursInMonth.model';

@Component({
  selector: 'app-update-hours',
  templateUrl: './update-hours.component.html',
  styleUrls: ['./update-hours.component.css']
})
export class UpdateHoursComponent implements OnInit {
  hours: HoursInMonth = new HoursInMonth();
  originalHours: HoursInMonth = new HoursInMonth();
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
      this.hours = navigation.extras.state['hours'] as HoursInMonth;
      if (this.hours) {
        this.originalHours = { ...this.hours };
      }
    }
  }

  ngOnInit(): void {
    if (!this.hours.idHoursInMonth) {
      console.error('Netika saņemti dati par darba stundām.');
      this.goBack();
    }
  }

  getMonthName(month: number): string {
    return this.monthNames[month];
  }

  onSubmit(): void {
    if (this.hours && this.hasChanges()) {
      this.hoursInMonthService.updateHoursInMonth(this.hours).subscribe({
        next: () => {
          this.router.navigate(['/hours-in-year', this.hours.year]);
        },
        error: (error) => {
          console.error('Dati netika atjaunoti:', error.message);
          alert('Dati netika atjaunoti, lūdzu, mēģiniet vēlreiz.');
        }
      });
    } else {
      alert('Izmaiņas netika veiktas.');
    }
  }

  goBack(): void {
    this.location.back();
  }

  hasChanges(): boolean {
    return JSON.stringify(this.originalHours) !== JSON.stringify(this.hours);
  }
}
