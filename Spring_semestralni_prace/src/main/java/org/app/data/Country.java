package org.app.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Country")
public class Country {
    @Id
    @Column(name="shortcut")
    private String shortcut;

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut(){
        return shortcut;
    }
}
