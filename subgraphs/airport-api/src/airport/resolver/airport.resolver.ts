import { Query, Resolver } from '@nestjs/graphql';
import { Airport } from '../model/airport';
import { AirportService } from '../service/airport.service';

@Resolver()
export class AirportResolver {
    constructor(private readonly airportService: AirportService) { }
    @Query(() => [Airport], { name: 'airports' })
    public async getAirports() {
        return this.airportService.getAllAirports();
    }
}
