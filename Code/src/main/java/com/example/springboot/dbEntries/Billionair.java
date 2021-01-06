package com.example.springboot.dbEntries;

import javax.persistence.*;

@Entity
@Table(name = "billionair")
public class Billionair {

    public Billionair(int id, String first_name, String last_name, String career){
        this.id = id;
        this.first_name = first_name;
        this.last_name =last_name;
        this.career = career;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String first_name;

    private String last_name;

    public String career;

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCareer() {
        return career;
    }

}