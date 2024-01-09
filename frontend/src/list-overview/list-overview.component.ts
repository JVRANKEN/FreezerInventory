import {Component, OnInit} from '@angular/core';
import {ListDetailComponent} from "../list-detail/list-detail.component";
import {Freezeritem} from "../shared/models/freezeritem";
import {FreezerService} from "../shared/services/freezer.service";
import {DatePipe, NgForOf} from "@angular/common";
import {Table, TableModule} from "primeng/table";
import {MultiSelectModule} from "primeng/multiselect";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {RippleModule} from "primeng/ripple";
import {SpeedDialModule} from "primeng/speeddial";
import {MenuItem} from "primeng/api";
import {ToolbarModule} from "primeng/toolbar";
import {Router} from "@angular/router";

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
    SpeedDialModule,
    ToolbarModule,
    DatePipe,
  ],
  templateUrl: './list-overview.component.html',
  styleUrl: './list-overview.component.css'
})
export class ListOverviewComponent implements OnInit {
  freezerItems: Freezeritem[];
  typesSet: Set<string>;
  distinctTypes: string [];
  menuItems: MenuItem[];

  constructor(private freezerService: FreezerService,
              private router: Router) {
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

    this.menuItems = [
      {
        icon: 'pi pi-pencil',
        command: () => {
          // this.messageService.add({ severity: 'info', summary: 'Add', detail: 'Data Added' });
        }
      },
      {
        icon: 'pi pi-refresh',
        command: () => {
          // this.messageService.add({ severity: 'success', summary: 'Update', detail: 'Data Updated' });
        }
      },
      {
        icon: 'pi pi-trash',
        command: () => {
          // this.messageService.add({ severity: 'error', summary: 'Delete', detail: 'Data Deleted' });
        }
      }
      // DELETE BUTTON + confirmation
      // {
      //   icon: 'pi pi-external-link',
      //   target: '_blank',
      //   url: 'http://angular.io'
      // }
    ];
    console.log('my items are =>', this.menuItems);


  }

  clear(table: Table) {
    table.clear();
  }

  createNewItem() {
    var newItem: Freezeritem;
    this.router.navigate(['detail'], {
        }
    );
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

  updateSelectedItem(freezeritem: Freezeritem) {
    console.log('selected item => ', freezeritem);
    freezeritem.existingItem = true;
    this.router.navigate(['detail'], {
        state: {response: {data: freezeritem}}
      }
    );
  }
}
