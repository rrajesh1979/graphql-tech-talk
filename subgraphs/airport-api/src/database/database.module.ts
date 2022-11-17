import { Module } from '@nestjs/common';
import { Db, MongoClient } from 'mongodb';
import { AirportRepository } from './repositories/airport-repository';
import { ConfigurationModule } from '../configuration/configuration.module';

@Module({
  imports: [ConfigurationModule],
  providers: [
    {
      provide: 'DATABASE_CONNECTION',
      inject: ['Application_Config'],
      useFactory: async (applicationConfig: any): Promise<Db> => {
        try {
          const client: MongoClient = await intitializaMongoClient(applicationConfig);
          const db: Db = client.db("graphql");
          return db;
        } catch (e) {
          console.log(e)
          throw e;
        }
      },
    },

    AirportRepository,
  ],
  exports: [
    AirportRepository,
  ],
})
export class DatabaseModule { }

async function intitializaMongoClient(applicationConfig: any) {
  const client: MongoClient = await new MongoClient(applicationConfig.mongoDbConnectionString, {
    monitorCommands: true
  }).connect();

  client.on("commandSucceeded", event => {
    const excludedEvents = ["isMaster", "buildInfo", "getLastError", "saslStart", "saslContinue"];
    if (!excludedEvents.includes(event.commandName)) {
      // logger.sendTelemetry("MongoDB", event.address, event.commandName, event.duration, true, "success");
    }
  });

  client.on("commandFailed", event => {
    const excludedEvents = ["isMaster", "buildInfo", "getLastError", "saslStart", "saslContinue"];
    if (!excludedEvents.includes(event.commandName)) {
      // logger.sendTelemetry("MongoDB", event.address, event.commandName, event.duration, false, "failed");
    }
  });
  return client;
}
