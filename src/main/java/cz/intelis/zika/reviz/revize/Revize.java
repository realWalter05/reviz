package cz.intelis.zika.reviz.revize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Changed fetch type to eager from lazy to get rid of the magical annotation
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "objednatele_id_objednatele")
    private Objednatele objednateleIdObjednatele;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_revidovane_objekty_revidovane_objekty")
    private RevidovaneObjekty idRevidovaneObjektyRevidovaneObjekty;
}