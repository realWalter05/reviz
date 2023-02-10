package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjednateleRepository
    extends JpaRepository<Objednatele, Long> {

    public List<Objednatele> getObjednatelesByNazev(String nazev);
}
