package com.rrajesh1979.graphqltechtalk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "crew_member")
public class CrewMemberDocument {
//    private ObjectId id;
    private String name;
    private String employeeNumber;
    private String position;
    private String base;

    private Assignment assignment;

    @Nullable
    private String schemaVersion;


}

