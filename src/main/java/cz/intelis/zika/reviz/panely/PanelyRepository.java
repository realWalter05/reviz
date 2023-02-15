package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanelyRepository
    extends JpaRepository<Panely, Long> {

    Panely getPanelyByVyrobniCislo(String vyrobniCislo);
    List<Panely> getPaneliesByIdRevizeRevize(Revize revize);
}
