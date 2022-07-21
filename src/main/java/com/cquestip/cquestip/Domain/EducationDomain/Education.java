package com.cquestip.cquestip.Domain.EducationDomain;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "education")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name="jsonb", typeClass = JsonBinaryType.class)
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
    private long educationid;
    private String institutename;

    private Double gpa;
    private LocalDate datejoined;

    private String institutelevel;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Experience> experiences = new ArrayList<Experience>();

//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> experiences;

    public Education(String institutename, Double gpa, LocalDate date, String instituteLevel) {
        this.institutename = institutename;
        this.gpa = gpa;
        this.datejoined = date;
        this.institutelevel = instituteLevel;
    }
}
