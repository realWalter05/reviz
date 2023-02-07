package cz.intelis.zika.reviz.revize;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@AllArgsConstructor
@RequestMapping("/api/revize")
public class RevizeApi {
    private final RevizeService revizeService;

    @GetMapping
    public List<Revize> findAll() {
        return revizeService.findAll();
    }
}
