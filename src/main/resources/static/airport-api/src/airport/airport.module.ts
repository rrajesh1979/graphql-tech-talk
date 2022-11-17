import { Module } from '@nestjs/common';
import { ConfigurationModule } from 'src/configuration/configuration.module';
import { DatabaseModule } from 'src/database/database.module';
import { AirportResolver } from './resolver/airport.resolver';
import { AirportService } from './service/airport.service';

@Module({
  imports: [
    DatabaseModule,
    ConfigurationModule
  ],
  providers: [AirportResolver, AirportService]
})
export class AirportModule { }
