package com.rrajesh1979.graphqltechtalk.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rrajesh1979.graphqltechtalk.model.CrewMember;
import com.rrajesh1979.graphqltechtalk.model.CrewMemberDoc;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CrewMemberMongoRepository {
    MongoClient mongoClient;
    MongoTemplate mongoTemplate;
    MongoDatabase graphqlDatabase;
    MongoCollection<Document> crewMemberCollection;

    @Autowired
    public CrewMemberMongoRepository(MongoClient mongoClient, MongoTemplate mongoTemplate) {
        this.mongoClient = mongoClient;
        this.mongoTemplate = mongoTemplate;
        this.graphqlDatabase = mongoClient.getDatabase("graphql");
        this.crewMemberCollection = graphqlDatabase.getCollection("crew_member");
    }

    //Get All Crew Members
    public List<CrewMemberDoc> allCrewMembers() {
        Query query = new Query();
        query.fields().exclude("_id");

        return mongoTemplate.find(query, CrewMemberDoc.class, "crew_member");
    }

    //Get Crew Member by Employee Number
    public CrewMember crewMemberByEmployeeNumber(String employeeNumber) {
        Query query = new Query();
        query.fields().exclude("_id");
        query.addCriteria(Criteria.where("employeeNumber").is(employeeNumber));

        return mongoTemplate.findOne(query, CrewMember.class, "crew_member");
    }

    public List<CrewMemberDoc> findCrewMembers(String employeeNumber, String base, String position) {
        Query query = new Query();
        query.fields().exclude("_id");

        if (employeeNumber != null) {
            query.addCriteria(Criteria.where("employeeNumber").is(employeeNumber));
        }
        if (base != null) {
            query.addCriteria(Criteria.where("base").is(base));
        }
        if (position != null) {
            query.addCriteria(Criteria.where("position").is(position));
        }

        List<CrewMemberDoc> crewMembers = mongoTemplate.find(query, CrewMemberDoc.class, "crew_member");
        log.info("CrewMembers: {}", crewMembers);
        return crewMembers;
    }


}
