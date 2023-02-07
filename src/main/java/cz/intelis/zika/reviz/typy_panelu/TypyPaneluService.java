package cz.intelis.zika.reviz.typy_panelu;

import cz.intelis.zika.reviz.stridace.Stridace;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypyPaneluService {
    private final TypyPaneluRepository typyPanelurepository;

    public TypyPaneluService(TypyPaneluRepository typyPaneluRepository) {
        this.typyPanelurepository = typyPaneluRepository;
    }

    public List<TypyPanelu> findAll() {
        return typyPanelurepository.findAll();
    }
}
