package com.rrajesh1979.graphqltechtalk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document @Getter @Setter @Slf4j
public class CrewMemberDoc {
    String name;
    String employeeNumber;
    String position;
    String base;
    Assignment assignment;
    String updatedTimeStamp;

    public CrewMemberDoc(String name, String employeeNumber, String position, String base, Assignment assignment, String updatedTimeStamp) {
        String strDate = parseDate(updatedTimeStamp);

        this.name = name;
        this.employeeNumber = employeeNumber;
        this.position = position;
        this.base = base;
        this.assignment = assignment;
        this.updatedTimeStamp = strDate;
    }

    private static String parseDate(String updatedTimeStamp) {
        String inputPattern = "E MMM dd HH:mm:ss z yyyy";
        String outputPattern = "yyyy-MM-dd";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputPattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(updatedTimeStamp);
        } catch (Exception e) {
            log.error("Error parsing date: " + e.getMessage());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(outputPattern);
        String strDate = dateFormat.format(date);

        return strDate;
    }

    //ToString
    @Override
    public String toString() {
        return "CrewMemberDoc{" +
                "name='" + name + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", position='" + position + '\'' +
                ", base='" + base + '\'' +
                ", assignment=" + assignment +
                ", updatedTimeStamp=" + updatedTimeStamp +
                '}';
    }
}
