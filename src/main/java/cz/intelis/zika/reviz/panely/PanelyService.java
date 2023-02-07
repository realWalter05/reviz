package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.revize.Revize;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;


@Service
public class PanelyService {
    private final PanelyRepository panelyRepository;

    public PanelyService(PanelyRepository panelyRepository) {
        this.panelyRepository = panelyRepository;
    }

    public List<Panely> findAll() {
        return panelyRepository.findAll();
    }
}
