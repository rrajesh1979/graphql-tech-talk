import { ObjectType, Field, Int, ID } from "@nestjs/graphql";

@ObjectType()
export class Airport {
    @Field((type) => ID)
    _id: number;

    @Field({ nullable: true })
    ident: string;

    @Field()
    type: string;

    @Field()
    name: string;

    @Field()
    latitude_deg: string;

    @Field()
    longitude_deg: string;

    @Field()
    elevation_ft: string;

    @Field({ nullable: true })
    continent: string;

    @Field({ nullable: true })
    iso_country: string;

    @Field({ nullable: true })
    iso_region: string;

    @Field({ nullable: true })
    municipality: string;

    @Field({ nullable: true })
    scheduled_service: string;

    @Field({ nullable: true })
    gps_code: string;

    @Field({ nullable: true })
    local_code: string;

    @Field({ nullable: true })
    iata_code: string;
}