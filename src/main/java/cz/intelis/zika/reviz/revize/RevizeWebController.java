package cz.intelis.zika.reviz.revize;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class RevizeWebController {

    private final RevizeService revizeService;
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/revize")
    public String updateUser(Model model) {
        List<Revize> revize = revizeService.findAll();
        model.addAttribute("revize", revize);
        return "revize";
    }
}
