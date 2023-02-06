package cz.intelis.zika.reviz.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "typy_panelu")
public class TypyPanelu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typy_panelu", nullable = false)
    private Long id;

    @Column(name = "typ", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String typ;

    @Column(name = "vykon", nullable = false)
    private Short vykon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Short getVykon() {
        return vykon;
    }

    public void setVykon(Short vykon) {
        this.vykon = vykon;
    }

}