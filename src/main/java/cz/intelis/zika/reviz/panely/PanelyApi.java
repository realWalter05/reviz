package cz.intelis.zika.reviz.panely;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/panely")
public class PanelyApi {
    private final PanelyService panelyService;

    @GetMapping
    public List<Panely> findAll() {
        return panelyService.findAll();
    }
}
