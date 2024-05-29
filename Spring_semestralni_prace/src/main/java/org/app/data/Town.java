package org.app.data;

import jakarta.persistence.*;

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
    @JoinColumn(name = "shortcut")
    private Country country;
}
