export class Freezeritem {
  documentId: string;
  item: string;
  category: string;
  type: string;
  quantity: number;
  weight: number;
  dateInput: Date;
  dateUpdated: Date;
  expiryDate: string | null;
  frozenDate: string | null;
  maxMonths: number;
  comment: string;
  existingItem?: boolean = false;
}
