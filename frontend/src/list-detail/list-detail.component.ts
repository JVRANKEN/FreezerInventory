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
    CalendarModule
  ],
  templateUrl: './list-detail.component.html',
  styleUrl: './list-detail.component.css'
})
export class ListDetailComponent {
  // @Input() item: Freezeritem;
//https://angular.io/guide/inputs-outputs
  public freezerItem: Freezeritem;
  public form: FormGroup;
  item: any;

  constructor(private router: Router,
              private fb: FormBuilder) {
    this.freezerItem = this.router.getCurrentNavigation()?.extras.state?.['response']?.['data'];
    console.log('my freezeritem in detail = ', this.freezerItem);
    this.createForm();
  }

  // TODO: item: ['', Validators.required]
  // TODO: need guard for date conversion,
  createForm() {
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

  onSubmit(myForm: any) {

  }
}
