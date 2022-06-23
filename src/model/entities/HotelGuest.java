package model.entities;

import java.security.Principal;

public abstract class HotelGuest {

    private String name;
    private Integer idNumber;

    public HotelGuest(String name, Integer idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

}
