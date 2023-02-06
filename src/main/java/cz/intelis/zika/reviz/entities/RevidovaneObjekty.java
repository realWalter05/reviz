package cz.intelis.zika.reviz.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "revidovane_objekty")
public class RevidovaneObjekty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revidovane_objekty", nullable = false)
    private Long id;

    @Column(name = "zeme", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String zeme;

    @Column(name = "mesto", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String mesto;

    @Column(name = "psc", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String psc;

    @Column(name = "ulice", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String ulice;

    @Column(name = "je_bytovy_dum", nullable = false)
    private Boolean jeBytovyDum = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZeme() {
        return zeme;
    }

    public void setZeme(String zeme) {
        this.zeme = zeme;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public Boolean getJeBytovyDum() {
        return jeBytovyDum;
    }

    public void setJeBytovyDum(Boolean jeBytovyDum) {
        this.jeBytovyDum = jeBytovyDum;
    }

}