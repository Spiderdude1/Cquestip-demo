package com.cquestip.cquestip.Domain.EducationDomain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.invoke.LambdaConversionException;
import java.time.LocalDate;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Experience implements Serializable {

    @Column(updatable = false)
    private int experienceid;

    private String name;
//    private LocalDate date;
//    private LocalDate eod;
    private String description;

}
