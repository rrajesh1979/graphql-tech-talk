import { Injectable } from '@nestjs/common';
import { AirportRepository } from 'src/database/repositories/airport-repository';
import { Airport } from '../model/airport';

@Injectable()
export class AirportService {
    constructor(
        private airportRepo: AirportRepository) {
    }

    public async getAirports(limit: number, skip: number): Promise<Airport[]> {
        return await this.airportRepo.findByConditionWithSortLimit({}, {}, limit, skip);
    }

    public async getAirportByCode(airportCode: string): Promise<Airport> {
        const filterDefinition = {
            iata_code: airportCode
        };
        return await this.airportRepo.findOneByCondition(filterDefinition);
    }
}
