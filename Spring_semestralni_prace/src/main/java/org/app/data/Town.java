package org.app.data;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Table(name="Town")
public class Town {
    @Id
    @Column(name="name")
    private String name;
    @Column(name="location")
    private String location;
    @Column(name="lat")
    private double lat;
    @Column(name="lon")
    private double lon;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "state_shortcut")
    private Country country;

    public void setAll(String name, String location, double lat, double lon, Country country){
        this.name = name;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public double getLon() {
        return lon;
    }

    public void setCountry(Country country){
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

}
