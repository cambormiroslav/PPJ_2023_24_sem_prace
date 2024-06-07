package org.app.data;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.util.Date;

public class CompositeKey implements Serializable {
    @Column(name = "date")
    private Date date;
    @OneToOne
    @JoinColumn(name = "town_name")
    private Town town;
}
