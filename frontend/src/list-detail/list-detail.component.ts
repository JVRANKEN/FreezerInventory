import {Component, Input} from '@angular/core';
import {DatePipe, formatDate, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {Freezeritem} from "../shared/models/freezeritem";
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {CalendarModule} from "primeng/calendar";
import {RippleModule} from "primeng/ripple";
import {FreezerService} from "../shared/services/freezer.service";

@Component({
  selector: 'app-list-detail',
  standalone: true,
  imports: [
    NgForOf,
    CardModule,
    ButtonModule,
    ReactiveFormsModule,
    JsonPipe,
    NgIf,
    InputTextModule,
    InputTextareaModule,
    CalendarModule,
    RippleModule
  ],
  templateUrl: './list-detail.component.html',
  styleUrl: './list-detail.component.css'
})
export class ListDetailComponent {
  // @Input() item: Freezeritem;
//https://angular.io/guide/inputs-outputs
  public freezerItem: Freezeritem = new Freezeritem();
  public freezerItemUpdated: Freezeritem;

  public form: FormGroup;
  item: any;

  constructor(private router: Router,
              private fb: FormBuilder,
              private freezerService: FreezerService,
              private datePipe: DatePipe) {
    this.freezerItem = this.router.getCurrentNavigation()?.extras.state?.['response']?.['data'];
    console.log('my freezeritem in detail = ', this.freezerItem);
    // TODO -> one create form method, and then map data on formbuilder
    if (this.freezerItem == undefined) {
      this.createFormWithoutData();
    } else {
      this.createFormWithData();
    }
  }

  // TODO: item: ['', Validators.required]
  // TODO: need guard for date conversion,
  // TODO date format in prop file? Or somewhere general?
  createFormWithData() {
    this.form = this.fb.group({
      documentId: [this.freezerItem.documentId],
      item: [this.freezerItem.item,],
      category: [this.freezerItem.category,],
      type: [this.freezerItem.type,],
      quantity: [this.freezerItem.quantity,],
      weight: [this.freezerItem.weight,],
      expiryDate: [this.datePipe.transform(this.freezerItem.expiryDate, 'dd-MM-yyyy'),],
      frozenDate: [this.datePipe.transform(this.freezerItem.frozenDate, 'dd-MM-yyyy'),],
      maxMonths: [this.freezerItem.maxMonths,],
      comment: [this.freezerItem.comment,]
    })
  }

  createFormWithoutData() {
    this.form = this.fb.group({
      documentId: [''],
      item: ['',],
      category: ['',],
      type: ['',],
      quantity: ['',],
      weight: ['',],
      expiryDate: ['01/01/2024',],
      frozenDate: ['01/01/2024',],
      maxMonths: ['',],
      comment: ['',]
    })
  }

  // TODO, add failsave, do not enter date > date today
  // TODO find a better way to convert dates
  onSubmit(myForm: any) {
    let formData = myForm.value as Freezeritem;
    console.log('my original data => ', this.freezerItem);
    console.log('my new data => ', formData);
    formData.expiryDate = (this.datePipe.transform(formData.expiryDate, 'yyyy-MM-dd'));
    formData.frozenDate = this.datePipe.transform(formData.frozenDate, 'yyyy-MM-dd');

    if (this.freezerItem != undefined && this.freezerItem.existingItem) {
      console.log('my form data new for update', formData);
      this.freezerService.updateFreezerItem(formData).subscribe(response => {
        // console.log('my response after update => ', response);
      });
    } else {
      console.log('my form data new for create', formData);
      this.freezerService.createFreezerItem(formData).subscribe(response => {
        // console.log('my response after update => ', response);
      });
    }
  }
}
