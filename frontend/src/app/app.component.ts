import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {ThemeService} from "./theme.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private themeService: ThemeService) {}

  title = 'frontend';
  selectedState: any = null;

  states: any[] = [
    {name: 'Arizona', code: 'Arizona'},
    {name: 'California', value: 'California'},
    {name: 'Florida', code: 'Florida'},
    {name: 'Ohio', code: 'Ohio'},
    {name: 'Washington', code: 'Washington'}
  ];

  cities1: any[] = [];

  cities2: any[] = [];

  city1:any = null;

  city2:any = null;

  changeTheme(theme: string) {
    this.themeService.switchTheme(theme);
  }
}
