package com.cquestip.cquestip.Domain.UserDomain;

import com.cquestip.cquestip.Domain.EducationDomain.Education;
import com.cquestip.cquestip.Domain.HashMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
//@JsonIgnoreProperties({"appUserRole"} )
@TypeDef(name="jsonb", typeClass = JsonBinaryType.class)
public class Student {

    @Id
    @SequenceGenerator(
       name = "Student_Sequence",
            sequenceName = "Student_Sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_Sequence"

    )
    private Long UserID;
    private String First;
    @Column(nullable = true)
    private String Middle;
    private String Last;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<Demographic> demographics = new ArrayList<Demographic>();
//    private Demographic demographic = new Demographic();

//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> demographic;

    @OneToMany(targetEntity = Education.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name= "Student_fk", referencedColumnName = "UserID")
    public List<Education> educations;

    public Student(String first, String middle, String last, String email, LocalDate dob) {
        this.First = first;
        this.Middle = middle;
        this.Last = last;
        this.email = email;
        this.dob = dob;
    }




    public int getAge()
    {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public List<Education> getEducations() {
        return educations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "UserID=" + UserID +
                ", First='" + First + '\'' +
                ", Middle='" + Middle + '\'' +
                ", Last='" + Last + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                "}";
    }


}
