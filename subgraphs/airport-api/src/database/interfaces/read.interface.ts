export interface Read<T> {
  find(item: T): Promise<T[]>;
  findAll(): Promise<T[]>;
  findOneById(id: string): Promise<T>;
  findOneByCondition(filterCondition : any): Promise<T>
  findByConditionWithSort(filterCondition: any, sortCondition: any, limit: number, skip: number): Promise<T[]>;
  findByCondition(filterCondition: any): Promise<T[]>;
}
