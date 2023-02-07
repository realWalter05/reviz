package cz.intelis.zika.reviz.objednatele;

import cz.intelis.zika.reviz.revize.Revize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/objednatele")
public class ObjednateleApi {
    private final ObjednateleService objednateleService;

    @GetMapping
    public List<Objednatele> findAll() {
        return objednateleService.findAll();
    }
}
