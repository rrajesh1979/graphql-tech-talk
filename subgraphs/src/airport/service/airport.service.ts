import { Injectable } from '@nestjs/common';
import { AirportRepository } from 'src/database/repositories/airport-repository';
import { Airport } from '../model/airport';

@Injectable()
export class AirportService {
    constructor(
        private authorizationRepo: AirportRepository) {
    }

    public async getAllAirports(): Promise<Airport[]> {
        return await this.authorizationRepo.findByConditionWithSortLimit({}, {}, 10, 0);
    }
}
