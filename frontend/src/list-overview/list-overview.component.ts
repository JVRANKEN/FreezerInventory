import {Component} from '@angular/core';
import {ListDetailComponent} from "../list-detail/list-detail.component";
import {Freezeritem} from "../shared/models/freezeritem";
import {FreezerService} from "../shared/services/freezer.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-list-overview',
  standalone: true,
  imports: [
    ListDetailComponent,
    NgForOf
  ],
  templateUrl: './list-overview.component.html',
  styleUrl: './list-overview.component.css'
})
export class ListOverviewComponent {

  freezerItems: Freezeritem[];

  constructor(private freezerService: FreezerService) {
    this.freezerItems = [];
  }

  ngOnInit() {
    this.freezerService.findAll().subscribe(data => {
      this.freezerItems = data;
      console.log('my freezeritems are =>' , this.freezerItems);
    });
  }
}
