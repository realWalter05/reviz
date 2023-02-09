package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelyRepository
    extends JpaRepository<Panely, Long> {

    public Panely getPanelyByVyrobniCislo(String vyrobniCislo);
}
