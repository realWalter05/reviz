package cz.intelis.zika.reviz.revize;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RevizeApi.class)
class RevizeApiTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevizeService revizeService;

    @Test
    void findAll() throws Exception {
        Revize revize0 = new Revize (
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                true,
                "wef",
                (short)6,
                (short)8,
                "asd",
                (short) 69,
                "das"
        );

        Revize revize1 = new Revize (
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                true,
                "wef",
                (short)6,
                (short)8,
                "asd",
                (short) 69,
                "das"
        );

        List<Revize> revize = new ArrayList<Revize>();
        revize.add(revize0);
        revize.add(revize1);

        Mockito.when(revizeService.findAll())
                .thenReturn(revize);

        mockMvc.perform(get("/revize")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].datumVypracovani").isNotEmpty())
                .andExpect(jsonPath("$[0].datumUkonceniRevize").isNotEmpty())
                .andExpect(jsonPath("$[0].datumPredaniRevize").isNotEmpty())
                .andExpect(jsonPath("$[0].jeNovaInstalace").isBoolean())
                .andExpect(jsonPath("$[0].distribucniSit").isString())
                .andExpect(jsonPath("$[0].pocetFazi").isNumber())
                .andExpect(jsonPath("$[0].pocetStringu").isNumber())
                .andExpect(jsonPath("$[0].jisteni").isString())
                .andExpect(jsonPath("$[0].prepetovaOchrana").isNumber())
                .andExpect(jsonPath("$[0].fotkaSrc").isString())
                .andExpect(jsonPath("$[1].datumVypracovani").isNotEmpty())
                .andExpect(jsonPath("$[1].datumUkonceniRevize").isNotEmpty())
                .andExpect(jsonPath("$[1].datumPredaniRevize").isNotEmpty())
                .andExpect(jsonPath("$[1].jeNovaInstalace").isBoolean())
                .andExpect(jsonPath("$[1].distribucniSit").isString())
                .andExpect(jsonPath("$[1].pocetFazi").isNumber())
                .andExpect(jsonPath("$[1].pocetStringu").isNumber())
                .andExpect(jsonPath("$[1].jisteni").isString())
                .andExpect(jsonPath("$[1].prepetovaOchrana").isNumber())
                .andExpect(jsonPath("$[1].fotkaSrc").isString());
    }
}