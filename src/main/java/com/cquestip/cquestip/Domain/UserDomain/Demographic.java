package com.cquestip.cquestip.Domain.UserDomain;

import lombok.*;

import java.io.Serializable;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Demographic implements Serializable {
    private String gender;
    private String race;
    private boolean disability;


    public boolean getDisability() {
        return disability;
    }

    public void setDemographic(Demographic demographic)
    {
        demographic.setGender(demographic.getGender());

        demographic.setRace(demographic.getRace());
        demographic.setDisability(demographic.getDisability());
    }
}
