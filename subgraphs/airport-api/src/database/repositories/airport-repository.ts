import { Inject, Injectable } from '@nestjs/common'
import { BaseRepository } from './base-repository'
import { Airport } from '../../airport/model/airport';
import { Db } from 'mongodb';

const AUTHORIZATION_UPDATE = "authorization";

@Injectable()
export class AirportRepository extends BaseRepository<Airport> {
  constructor(
    @Inject('DATABASE_CONNECTION')
    db: Db,
  ) {
    super(Airport, db, 'airports')
  }

  async aggregation(pipeline: any[], options?: any): Promise<any> {
    const result = await this._collection.aggregate(pipeline, options).toArray()

    return result
  }
}
