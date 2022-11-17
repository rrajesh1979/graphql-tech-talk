import { ApolloFederationDriver } from '@nestjs/apollo';
import { Module } from '@nestjs/common';
import { GraphQLModule } from '@nestjs/graphql';
import { join } from 'path';
import { AirportModule } from './airport/airport.module';
import { ConfigModule } from '@nestjs/config';
import configuration from './configuration/configuration';
@Module({
  imports: [
    GraphQLModule.forRoot({
      debug: false,
      playground: true,
      driver: ApolloFederationDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema/schema.gql'),
      context: ({ req }) => ({ req }),
    }),
    ConfigModule.forRoot({
      isGlobal: true,
      load: [configuration],
    }),
    AirportModule,
  ],
})
export class AppModule { }
