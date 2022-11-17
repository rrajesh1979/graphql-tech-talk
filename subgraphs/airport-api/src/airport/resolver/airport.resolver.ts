import { Args, Int, Query, Resolver } from '@nestjs/graphql';
import { Airport } from '../model/airport';
import { AirportService } from '../service/airport.service';

@Resolver()
export class AirportResolver {
    constructor(private readonly airportService: AirportService) { }

    @Query(() => [Airport], { name: 'airports' })
    public async getAirports(
        @Args("limit", { type: () => Int, defaultValue: 100 }) limit: number,
        @Args("skip", { type: () => Int, defaultValue: 0 }) skip: number) {
        return this.airportService.getAirports(limit, skip);
    }

    @Query(() => Airport, { name: 'airportByCode' })
    public async getAirportByCode(
        @Args("code", { type: () => String }) code: string) {
        return this.airportService.getAirportByCode(code);
    }
}
