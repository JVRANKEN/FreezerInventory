export class Freezeritem {
  documentId: string;
  item: string;
  category: string;
  type: string;
  quantity: number;
  weight: number;
  expiryDate: string | null;
  frozenDate: string | null;
  maxMonths: number;
  comment: string;
  existingItem?: boolean = false;
}
