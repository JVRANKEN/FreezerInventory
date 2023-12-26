import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {Freezeritem} from "../shared/models/freezeritem";
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-detail',
  standalone: true,
  imports: [
    NgForOf,
    CardModule,
    ButtonModule
  ],
  templateUrl: './list-detail.component.html',
  styleUrl: './list-detail.component.css'
})
export class ListDetailComponent {
  // @Input() item: Freezeritem;
//https://angular.io/guide/inputs-outputs
  freezerItem: Freezeritem;

  constructor(private router: Router) {
    this.freezerItem = this.router.getCurrentNavigation()?.extras.state?.['response']?.['data'];
    console.log('my freezeritem in detail = ', this.freezerItem);
  }
}
