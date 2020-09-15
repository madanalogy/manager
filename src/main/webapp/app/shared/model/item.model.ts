export interface IItem {
  id?: string;
  name?: string;
  cost?: number;
}

export class Item implements IItem {
  constructor(public id?: string, public name?: string, public cost?: number) {}
}
