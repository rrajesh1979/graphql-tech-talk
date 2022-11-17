import { Test, TestingModule } from '@nestjs/testing';
import { AirportResolver } from './airport.resolver';

describe('AirportResolver', () => {
  let resolver: AirportResolver;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [AirportResolver],
    }).compile();

    resolver = module.get<AirportResolver>(AirportResolver);
  });

  it('should be defined', () => {
    expect(resolver).toBeDefined();
  });
});
