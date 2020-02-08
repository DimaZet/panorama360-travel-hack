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
    private byte[] originalImage;

    @Lob
    @Column(name = "edited_photo", nullable = false)
    private byte[] editedImage;

    public Image() {
    }

    public Image(String barcode, byte[] originalImage, byte[] editedImage) {
        this.barcode = barcode;
        this.originalImage = originalImage;
        this.editedImage = editedImage;
    }

    public Long getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public byte[] getEditedImage() {
        return editedImage;
    }

    public void setEditedImage(byte[] editedImage) {
        this.editedImage = editedImage;
    }

    public byte[] getOriginalImage() {
        return originalImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setOriginalImage(byte[] originalImage) {
        this.originalImage = originalImage;
    }
}
