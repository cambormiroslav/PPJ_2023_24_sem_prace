package org.app.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="Country")
public class Country {
    @Id
    @Column(name="shortcut")
    private String shortcut;
    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut(){
        return shortcut;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    @Override
    public String toString(){
        return String.format("Country: {shortcut : %s}", shortcut);
    }
}
