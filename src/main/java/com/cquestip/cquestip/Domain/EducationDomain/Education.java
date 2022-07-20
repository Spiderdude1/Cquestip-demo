package com.cquestip.cquestip.Domain.EducationDomain;


import com.cquestip.cquestip.Domain.HashMapConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @SequenceGenerator(
            name = "Education_Sequence",
            sequenceName = "Education_Sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Education_Sequence"

    )
    private long id;
    private String instituteName;
    private Double gpa;
    private LocalDate date;

    private String instituteLevel;

    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> experiences;

    public Education(String instituteName, Double gpa, LocalDate date, String instituteLevel) {
        this.instituteName = instituteName;
        this.gpa = gpa;
        this.date = date;
        this.instituteLevel = instituteLevel;
    }
}
