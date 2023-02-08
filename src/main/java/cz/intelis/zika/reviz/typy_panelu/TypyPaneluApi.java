package cz.intelis.zika.reviz.typy_panelu;

import cz.intelis.zika.reviz.stridace.Stridace;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/typy_panelu")
public class TypyPaneluApi {
    private final TypyPaneluService typyPaneluService;

    @GetMapping
    public List<TypyPanelu> findAll() {
        return typyPaneluService.findAll();
    }
}
