import { plainToClass } from 'class-transformer'
import { Collection, Db, InsertOneResult, BulkWriteResult, AggregateOptions, ReturnDocument } from 'mongodb'
import { Read } from '../interfaces/read.interface'
import { Write } from '../interfaces/write.interface'
import { v4 as uuidv4 } from 'uuid';
export abstract class BaseRepository<T> implements Write<T>, Read<T> {
  public readonly _collection: Collection

  constructor(protected classToTransform: any, db: Db, collectionName: string) {
    this._collection = db.collection(collectionName)
  }

  async findAll(): Promise<T[]> {
    const result = await this._collection.find().toArray()
    return plainToClass(this.classToTransform, result)
  }

  async findOneById(id): Promise<T> {
    const filterDefinition = {
      _id: id,
    }
    const result = await this._collection.findOne(filterDefinition)

    return plainToClass(this.classToTransform, result)
  }

  async aggregationWithOrderAndPagination(pipeline: any[], sortBy: string = null, ascendingOrder: boolean = true, skip: number = null, limit: number = null, options?: any): Promise<any[]> {
    if (sortBy != null) {
      pipeline.push(
        {
          $sort: { [sortBy]: ascendingOrder ? 1 : -1 }
        }
      )
      options =
      {
        collation:
        {
          locale: "en", caseLevel: true
        }
      }
    }
    if (skip != null) {
      pipeline.push(
        {
          $skip: skip
        },
        {
          $limit: limit
        }
      )
    }
    return await this.aggregation(pipeline, options)
  }

  async aggregation(pipeline: any[], options?: any): Promise<T[]> {
    const result = await this._collection.aggregate(pipeline, options).toArray()
    return plainToClass(this.classToTransform, result)
  }

  async findByConditionWithSort(
    filterCondition: any,
    sortCondition: any
  ): Promise<T[]> {
    const result = await this._collection
      .find(filterCondition)
      .sort(sortCondition)
      .toArray()

    return plainToClass(this.classToTransform, result)
  }

  async findByConditionWithSortLimit(
    filterCondition: any,
    sortCondition: any,
    limit: number,
    skip: number,
  ): Promise<T[]> {
    const result = await this._collection
      .find(filterCondition)
      .sort(sortCondition)
      .skip(skip)
      .limit(limit)
      .toArray()

    return plainToClass(this.classToTransform, result)
  }

  async findByCondition(filterCondition: any): Promise<T[]> {
    const result = await this._collection.find(filterCondition).toArray()

    return plainToClass(this.classToTransform, result)
  }

  async findOneByCondition(filterDefinition): Promise<T> {
    const result = await this._collection.findOne(filterDefinition)

    return plainToClass(this.classToTransform, result);
  }

  find(item: T): Promise<T[]> {
    throw new Error('Method not implemented.')
  }

  async create(item: T): Promise<boolean> {
    const result: InsertOneResult = await this._collection.insertOne(item)
    return !!result.acknowledged
  }

  async update(filter: any, updates: any): Promise<boolean> {
    const result = await this._collection.updateMany(filter, updates, {
      upsert: false,
    })

    return result.acknowledged && result.modifiedCount > 0
  }

  async updateByFilter(filter: any, updates: any): Promise<boolean> {
    const result = await this._collection.updateOne(filter, updates, {
      upsert: false,
    })

    return result.acknowledged && result.modifiedCount > 0
  }

  async updateManyByFilter(filter: any, updates: any): Promise<boolean> {
    const result = await this._collection.updateMany(filter, updates, {
      upsert: false,
    })

    return result.acknowledged && result.modifiedCount > 0
  }

  async bulkWrite(operations: any[]): Promise<BulkWriteResult> {
    const result: BulkWriteResult = await this._collection.bulkWrite(operations)
    return result
  }

  async findOneAndupdate(filter: any, updates: any): Promise<T> {
    const result = await this._collection.findOneAndUpdate(filter, updates, {
      upsert: false,
      returnDocument: ReturnDocument.AFTER
    })

    return plainToClass(this.classToTransform, result.value);
  }

  async delete(id: string): Promise<boolean> {
    const filterDefinition = {
      _id: id,
    }
    const result = await this._collection.deleteOne(filterDefinition);

    return !!(result.deletedCount > 0)
  }

  async deleteMany(filter: any): Promise<boolean> {
    const result = await this._collection.deleteMany(filter)
    return result.acknowledged
  }

  async generateUuidForaDoc(): Promise<string> {
    return uuidv4();
  }

  count(filterCondition: any): Promise<number> {
    const result = this._collection.countDocuments(filterCondition)
    return plainToClass(this.classToTransform, result)
  }
}

