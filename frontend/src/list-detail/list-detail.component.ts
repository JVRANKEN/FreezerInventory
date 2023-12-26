import {Component, Input} from '@angular/core';
import {JsonPipe, NgForOf, NgIf} from "@angular/common";
import {Freezeritem} from "../shared/models/freezeritem";
import {CardModule} from "primeng/card";
import {ButtonModule} from "primeng/button";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {CalendarModule} from "primeng/calendar";
import {RippleModule} from "primeng/ripple";

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
  public freezerItemUpdated: Freezeritem ;

  public form: FormGroup;
  item: any;

  constructor(private router: Router,
              private fb: FormBuilder) {
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
  createFormWithData() {
    this.form = this.fb.group({
      item: [this.freezerItem.item,],
      category: [this.freezerItem.category,],
      type: [this.freezerItem.type,],
      quantity: [this.freezerItem.quantity,],
      weight: [this.freezerItem.weight,],
      expiryDate: ['01/01/2024',],
      frozenDate: ['01/01/2024',],
      maxMonths: [this.freezerItem.maxMonths,],
      comment: [this.freezerItem.comment,]
    })
  }

  createFormWithoutData() {
    this.form = this.fb.group({
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

  onSubmit(myForm: any) {
    let formData = myForm.value as Freezeritem;
    if(this.freezerItem != undefined && this.freezerItem.existingItem) {
      console.log('This is an existing dataset');
      console.log('original data => ', this.freezerItem);
      console.log('new data => ', myForm);
      console.log('my new freezer item =>' , formData)
    } else {
      console.log('This is a new dataset');
      console.log('original data => ', this.freezerItem);
      console.log('new data => ', formData);
    }
  }
}
