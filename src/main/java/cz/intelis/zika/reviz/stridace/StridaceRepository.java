package cz.intelis.zika.reviz.stridace;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StridaceRepository
    extends JpaRepository<Stridace, Long> {

    List<Stridace> getStridacesByVyrobce(String vyrobce);
    List<Stridace> getStridacesByVyrobniCislo(String vyrobniCislo);
}
