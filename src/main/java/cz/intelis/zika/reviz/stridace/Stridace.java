package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}