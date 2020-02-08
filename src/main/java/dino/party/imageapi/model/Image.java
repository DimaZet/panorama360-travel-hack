package dino.party.imageapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "photos")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "barcode", length = 50, nullable = false)
    private String barcode;

    @Lob
    @Column(name = "original_photo", nullable = false)
    private byte[] image;

    public Image() {
    }

    public Image(String barcode, byte[] image) {
        this.barcode = barcode;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
