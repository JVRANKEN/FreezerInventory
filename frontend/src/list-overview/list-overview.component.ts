import { Component } from '@angular/core';
import {ListDetailComponent} from "../list-detail/list-detail.component";

@Component({
  selector: 'app-list-overview',
  standalone: true,
  imports: [
    ListDetailComponent
  ],
  templateUrl: './list-overview.component.html',
  styleUrl: './list-overview.component.css'
})
export class ListOverviewComponent {

}
