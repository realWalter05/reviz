package cz.intelis.zika.reviz.revidovane_objekty;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevidovaneObjektyRepository
    extends JpaRepository<RevidovaneObjekty, Long> {

    public List<RevidovaneObjekty> getRevidovaneObjektiesByJeBytovyDum(Boolean jeBytovyDum);
}
