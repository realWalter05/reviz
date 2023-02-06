package cz.intelis.zika.reviz.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "panely")
public class Panely {
    @Id
    @Column(name = "id_panely", nullable = false)
    private Long id;

    @Column(name = "vyrobni_cislo", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String vyrobniCislo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_typy_panelu_typy_panelu")
    private TypyPanelu idTypyPaneluTypyPanelu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_revize_revize")
    private Revize idRevizeRevize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVyrobniCislo() {
        return vyrobniCislo;
    }

    public void setVyrobniCislo(String vyrobniCislo) {
        this.vyrobniCislo = vyrobniCislo;
    }

    public TypyPanelu getIdTypyPaneluTypyPanelu() {
        return idTypyPaneluTypyPanelu;
    }

    public void setIdTypyPaneluTypyPanelu(TypyPanelu idTypyPaneluTypyPanelu) {
        this.idTypyPaneluTypyPanelu = idTypyPaneluTypyPanelu;
    }

    public Revize getIdRevizeRevize() {
        return idRevizeRevize;
    }

    public void setIdRevizeRevize(Revize idRevizeRevize) {
        this.idRevizeRevize = idRevizeRevize;
    }

}