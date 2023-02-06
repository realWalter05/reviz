package cz.intelis.zika.reviz.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "stridace")
public class Stridace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stridace", nullable = false)
    private Long id;

    @Column(name = "pocet", nullable = false)
    private Short pocet;

    @Column(name = "nazev", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String nazev;

    @Column(name = "vyrobce", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String vyrobce;

    @Column(name = "vyrobni_cislo", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String vyrobniCislo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_revize_revize")
    private Revize idRevizeRevize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getPocet() {
        return pocet;
    }

    public void setPocet(Short pocet) {
        this.pocet = pocet;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getVyrobce() {
        return vyrobce;
    }

    public void setVyrobce(String vyrobce) {
        this.vyrobce = vyrobce;
    }

    public String getVyrobniCislo() {
        return vyrobniCislo;
    }

    public void setVyrobniCislo(String vyrobniCislo) {
        this.vyrobniCislo = vyrobniCislo;
    }

    public Revize getIdRevizeRevize() {
        return idRevizeRevize;
    }

    public void setIdRevizeRevize(Revize idRevizeRevize) {
        this.idRevizeRevize = idRevizeRevize;
    }

}