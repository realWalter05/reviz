package cz.intelis.zika.reviz.revize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevizeRepository
    extends JpaRepository<Revize, Long> {

    List<Revize> getRevizeByDatumPredaniRevizeBetween(LocalDate datumPredaniRevizeOd, LocalDate datumPredaniRevizeDo);
}
