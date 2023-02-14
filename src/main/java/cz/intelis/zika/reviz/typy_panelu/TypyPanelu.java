package cz.intelis.zika.reviz.typy_panelu;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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