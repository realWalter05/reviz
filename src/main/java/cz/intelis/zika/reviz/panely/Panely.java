package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.revize.Revize;
import cz.intelis.zika.reviz.typy_panelu.TypyPanelu;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "panely")
public class Panely {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Panely(Long id, String vyrobniCislo, TypyPanelu idTypyPaneluTypyPanelu, Revize idRevizeRevize) {
        this.id = id;
        this.vyrobniCislo = vyrobniCislo;
        this.idTypyPaneluTypyPanelu = idTypyPaneluTypyPanelu;
        this.idRevizeRevize = idRevizeRevize;
    }
}