package cz.intelis.zika.reviz.revize;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RevizeService {
    private final RevizeRepository revizeRepository;

    public RevizeService(RevizeRepository revizeRepository) {
        this.revizeRepository = revizeRepository;
    }

    public List<Revize> findAll() {
        return revizeRepository.findAll();
    }
}
