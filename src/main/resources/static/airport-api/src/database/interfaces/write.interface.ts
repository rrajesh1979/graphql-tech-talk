export interface Write<T> {
  create(item: T | any): Promise<boolean>;
  update(id: string, item: T): Promise<boolean>;
  delete(id: string): Promise<boolean>;
  updateByFilter(filter: any, updates: any): Promise<Boolean>;
}
