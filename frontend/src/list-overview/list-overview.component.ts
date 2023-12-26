import {Component} from '@angular/core';
import {ListDetailComponent} from "../list-detail/list-detail.component";
import {Freezeritem} from "../shared/models/freezeritem";
import {FreezerService} from "../shared/services/freezer.service";
import {NgForOf} from "@angular/common";
import {Table, TableModule} from "primeng/table";
import {MultiSelectModule} from "primeng/multiselect";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {RippleModule} from "primeng/ripple";
import {SpeedDialModule} from "primeng/speeddial";

@Component({
  selector: 'app-list-overview',
  standalone: true,
  imports: [
    ListDetailComponent,
    NgForOf,
    TableModule,
    MultiSelectModule,
    FormsModule,
    ButtonModule,
    InputTextModule,
    RippleModule,
    SpeedDialModule
  ],
  templateUrl: './list-overview.component.html',
  styleUrl: './list-overview.component.css'
})
export class ListOverviewComponent {

  freezerItems: Freezeritem[];
  typesSet: Set<string>;
  distinctTypes: string [];

  constructor(private freezerService: FreezerService) {
    this.freezerItems = [];
    this.distinctTypes = [];
    this.typesSet = new Set<string>();
  }

  ngOnInit() {
    this.freezerService.findAll().subscribe(data => {
      this.freezerItems = data;
      console.log('my freezeritems are =>', this.freezerItems);
      for (const item of data) {
        this.typesSet.add(item.category);
      }
      this.distinctTypes = Array.from(this.typesSet);
    });
  }

  clear(table: Table) {
    table.clear();
  }

  // getSeverity(status: string) {
  //   switch (status.toLowerCase()) {
  //     case 'unqualified':
  //       return 'danger';
  //
  //     case 'qualified':
  //       return 'success';
  //
  //     case 'new':
  //       return 'info';
  //
  //     case 'negotiation':
  //       return 'warning';
  //
  //     case 'renewal':
  //       return null;
  //   }
  // }
}
