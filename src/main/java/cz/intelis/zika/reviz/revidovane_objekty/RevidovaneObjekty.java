package cz.intelis.zika.reviz.revidovane_objekty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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

    public RevidovaneObjekty(String zeme, String mesto, String psc, String ulice, Boolean jeBytovyDum) {
        this.zeme = zeme;
        this.mesto = mesto;
        this.psc = psc;
        this.ulice = ulice;
        this.jeBytovyDum = jeBytovyDum;
    }
}