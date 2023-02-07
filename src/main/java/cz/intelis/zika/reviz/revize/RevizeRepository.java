package cz.intelis.zika.reviz.revize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevizeRepository
    extends JpaRepository<Revize, Long> {

}
