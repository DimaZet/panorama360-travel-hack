package dino.party.imageapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "is_in_que")
    private boolean inQue;

    public User() {
    }

    public User(String barcode, boolean active, boolean inQue) {
        this.barcode = barcode;
        this.active = active;
        this.inQue = inQue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isInQue() {
        return inQue;
    }

    public void setInQue(boolean inQue) {
        this.inQue = inQue;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
