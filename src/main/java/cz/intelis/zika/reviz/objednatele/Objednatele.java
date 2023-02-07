package cz.intelis.zika.reviz.objednatele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "objednatele")
public class Objednatele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objednatele_id", nullable = false)
    private Long id;

    @Column(name = "nazev", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String nazev;

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

    public Objednatele(String nazev, String zeme, String mesto, String psc, String ulice) {
        this.nazev = nazev;
        this.zeme = zeme;
        this.mesto = mesto;
        this.psc = psc;
        this.ulice = ulice;
    }
}