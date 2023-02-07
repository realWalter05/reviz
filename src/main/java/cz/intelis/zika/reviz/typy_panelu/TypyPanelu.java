package cz.intelis.zika.reviz.typy_panelu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "typy_panelu")
public class TypyPanelu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typy_panelu", nullable = false)
    private Long id;

    @Column(name = "typ", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String typ;

    @Column(name = "vykon", nullable = false)
    private Short vykon;
}