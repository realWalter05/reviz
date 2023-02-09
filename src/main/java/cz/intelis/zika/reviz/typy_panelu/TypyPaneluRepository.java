package cz.intelis.zika.reviz.typy_panelu;

import cz.intelis.zika.reviz.stridace.Stridace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypyPaneluRepository
    extends JpaRepository<TypyPanelu, Long> {

    public List<TypyPanelu> getTypyPanelusByTyp(String typ);
}
