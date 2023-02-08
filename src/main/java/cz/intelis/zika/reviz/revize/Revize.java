package cz.intelis.zika.reviz.revize;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "revize")
public class Revize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revize", nullable = false)
    private Long id;

    @Column(name = "datum_vypracovani", nullable = false)
    private LocalDate datumVypracovani;

    @Column(name = "datum_ukonceni_revize", nullable = false)
    private LocalDate datumUkonceniRevize;

    @Column(name = "datum_predani_revize", nullable = false)
    private LocalDate datumPredaniRevize;

    @Column(name = "je_nova_instalace", nullable = false)
    private Boolean jeNovaInstalace = false;

    @Column(name = "distribucni_sit", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String distribucniSit;

    @Column(name = "pocet_fazi", nullable = false)
    private Short pocetFazi;

    @Column(name = "pocet_stringu", nullable = false)
    private Short pocetStringu;

    @Column(name = "jisteni", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String jisteni;

    @Column(name = "prepetova_ochrana", nullable = false)
    private Short prepetovaOchrana;

    @Column(name = "fotka_src", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String fotkaSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objednatele_id_objednatele")
    private Objednatele objednateleIdObjednatele;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_revidovane_objekty_revidovane_objekty")
    private RevidovaneObjekty idRevidovaneObjektyRevidovaneObjekty;

    public Revize(LocalDate datumVypracovani, LocalDate datumUkonceniRevize, LocalDate datumPredaniRevize, Boolean jeNovaInstalace, String distribucniSit, Short pocetFazi, Short pocetStringu, String jisteni, Short prepetovaOchrana, String fotkaSrc) {
        this.datumVypracovani = datumVypracovani;
        this.datumUkonceniRevize = datumUkonceniRevize;
        this.datumPredaniRevize = datumPredaniRevize;
        this.jeNovaInstalace = jeNovaInstalace;
        this.distribucniSit = distribucniSit;
        this.pocetFazi = pocetFazi;
        this.pocetStringu = pocetStringu;
        this.jisteni = jisteni;
        this.prepetovaOchrana = prepetovaOchrana;
        this.fotkaSrc = fotkaSrc;
    }
}