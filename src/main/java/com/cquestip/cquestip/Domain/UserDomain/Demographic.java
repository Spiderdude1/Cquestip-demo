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


}
