package cz.intelis.zika.reviz.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatumVypracovani() {
        return datumVypracovani;
    }

    public void setDatumVypracovani(LocalDate datumVypracovani) {
        this.datumVypracovani = datumVypracovani;
    }

    public LocalDate getDatumUkonceniRevize() {
        return datumUkonceniRevize;
    }

    public void setDatumUkonceniRevize(LocalDate datumUkonceniRevize) {
        this.datumUkonceniRevize = datumUkonceniRevize;
    }

    public LocalDate getDatumPredaniRevize() {
        return datumPredaniRevize;
    }

    public void setDatumPredaniRevize(LocalDate datumPredaniRevize) {
        this.datumPredaniRevize = datumPredaniRevize;
    }

    public Boolean getJeNovaInstalace() {
        return jeNovaInstalace;
    }

    public void setJeNovaInstalace(Boolean jeNovaInstalace) {
        this.jeNovaInstalace = jeNovaInstalace;
    }

    public String getDistribucniSit() {
        return distribucniSit;
    }

    public void setDistribucniSit(String distribucniSit) {
        this.distribucniSit = distribucniSit;
    }

    public Short getPocetFazi() {
        return pocetFazi;
    }

    public void setPocetFazi(Short pocetFazi) {
        this.pocetFazi = pocetFazi;
    }

    public Short getPocetStringu() {
        return pocetStringu;
    }

    public void setPocetStringu(Short pocetStringu) {
        this.pocetStringu = pocetStringu;
    }

    public String getJisteni() {
        return jisteni;
    }

    public void setJisteni(String jisteni) {
        this.jisteni = jisteni;
    }

    public Short getPrepetovaOchrana() {
        return prepetovaOchrana;
    }

    public void setPrepetovaOchrana(Short prepetovaOchrana) {
        this.prepetovaOchrana = prepetovaOchrana;
    }

    public String getFotkaSrc() {
        return fotkaSrc;
    }

    public void setFotkaSrc(String fotkaSrc) {
        this.fotkaSrc = fotkaSrc;
    }

    public Objednatele getObjednateleIdObjednatele() {
        return objednateleIdObjednatele;
    }

    public void setObjednateleIdObjednatele(Objednatele objednateleIdObjednatele) {
        this.objednateleIdObjednatele = objednateleIdObjednatele;
    }

    public RevidovaneObjekty getIdRevidovaneObjektyRevidovaneObjekty() {
        return idRevidovaneObjektyRevidovaneObjekty;
    }

    public void setIdRevidovaneObjektyRevidovaneObjekty(RevidovaneObjekty idRevidovaneObjektyRevidovaneObjekty) {
        this.idRevidovaneObjektyRevidovaneObjekty = idRevidovaneObjektyRevidovaneObjekty;
    }

}