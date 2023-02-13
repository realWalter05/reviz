package cz.intelis.zika.reviz.revize;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RevizeApi.class)
class RevizeApiTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevizeService revizeService;

    private Revize revizeExample0 = new Revize (
            (long)1,
            LocalDate.of(2005, Month.DECEMBER, 29),
            LocalDate.of(2005, Month.DECEMBER, 29),
            LocalDate.of(2005, Month.DECEMBER, 29),
            true,
            "wef",
            (short)6,
            (short)8,
            "asd",
            (short) 69,
            "das",
            null,
            null
    );

    @Test
    void findAll() throws Exception {
        Revize revize1 = new Revize (
                (long) 2,
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                LocalDate.of(2005, Month.DECEMBER, 29),
                true,
                "wef",
                (short)6,
                (short)8,
                "asd",
                (short) 69,
                "das",
                null,
                null
        );

        List<Revize> revize = new ArrayList<Revize>();
        revize.add(revizeExample0);
        revize.add(revize1);

        Mockito.when(revizeService.findAll())
                .thenReturn(revize);

        mockMvc.perform(get("/revize")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(revize.get(0).getId()))
                .andExpect(jsonPath("$[0].datumVypracovani").value(revize.get(0).getDatumVypracovani().toString()))
                .andExpect(jsonPath("$[0].datumUkonceniRevize").value(revize.get(0).getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$[0].datumPredaniRevize").value(revize.get(0).getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$[0].jeNovaInstalace").value(revize.get(0).getJeNovaInstalace()))
                .andExpect(jsonPath("$[0].distribucniSit").value(revize.get(0).getDistribucniSit()))
                .andExpect(jsonPath("$[0].pocetFazi").value(revize.get(0).getPocetFazi().toString()))
                .andExpect(jsonPath("$[0].pocetStringu").value(revize.get(0).getPocetStringu().toString()))
                .andExpect(jsonPath("$[0].jisteni").value(revize.get(0).getJisteni()))
                .andExpect(jsonPath("$[0].prepetovaOchrana").value(revize.get(0).getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$[0].fotkaSrc").value(revize.get(0).getFotkaSrc()))
                .andExpect(jsonPath("$[1].id").value(revize.get(1).getId()))
                .andExpect(jsonPath("$[1].datumVypracovani").value(revize.get(1).getDatumVypracovani().toString()))
                .andExpect(jsonPath("$[1].datumUkonceniRevize").value(revize.get(1).getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$[1].datumPredaniRevize").value(revize.get(1).getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$[1].jeNovaInstalace").value(revize.get(1).getJeNovaInstalace()))
                .andExpect(jsonPath("$[1].distribucniSit").value(revize.get(1).getDistribucniSit()))
                .andExpect(jsonPath("$[1].pocetFazi").value(revize.get(1).getPocetFazi().toString()))
                .andExpect(jsonPath("$[1].pocetStringu").value(revize.get(1).getPocetStringu().toString()))
                .andExpect(jsonPath("$[1].jisteni").value(revize.get(1).getJisteni()))
                .andExpect(jsonPath("$[1].prepetovaOchrana").value(revize.get(1).getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$[1].fotkaSrc").value(revize.get(1).getFotkaSrc()));
    }

    @Test
    void findById() throws Exception {
        Mockito.when(revizeService.findById(revizeExample0.getId())).thenReturn(Optional.of(revizeExample0));

        mockMvc.perform(get("/revize/"+revizeExample0.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(revizeExample0.getId()))
                .andExpect(jsonPath("$.datumVypracovani").value(revizeExample0.getDatumVypracovani().toString()))
                .andExpect(jsonPath("$.datumUkonceniRevize").value(revizeExample0.getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$.datumPredaniRevize").value(revizeExample0.getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$.jeNovaInstalace").value(revizeExample0.getJeNovaInstalace()))
                .andExpect(jsonPath("$.distribucniSit").value(revizeExample0.getDistribucniSit()))
                .andExpect(jsonPath("$.pocetFazi").value(revizeExample0.getPocetFazi().toString()))
                .andExpect(jsonPath("$.pocetStringu").value(revizeExample0.getPocetStringu().toString()))
                .andExpect(jsonPath("$.jisteni").value(revizeExample0.getJisteni()))
                .andExpect(jsonPath("$.prepetovaOchrana").value(revizeExample0.getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$.fotkaSrc").value(revizeExample0.getFotkaSrc()));
    }

    @Test
    void delete_test() {
        // TODO
    }

    @Test
    void create() throws Exception {
        Mockito.when(revizeService.create(revizeExample0)).thenReturn(revizeExample0);

        // Had to install special json dependency for this to work
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        mockMvc.perform(post("/revize/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(revizeExample0))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(revizeExample0.getId()))
                .andExpect(jsonPath("$.datumVypracovani").value(revizeExample0.getDatumVypracovani().toString()))
                .andExpect(jsonPath("$.datumUkonceniRevize").value(revizeExample0.getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$.datumPredaniRevize").value(revizeExample0.getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$.jeNovaInstalace").value(revizeExample0.getJeNovaInstalace()))
                .andExpect(jsonPath("$.distribucniSit").value(revizeExample0.getDistribucniSit()))
                .andExpect(jsonPath("$.pocetFazi").value(revizeExample0.getPocetFazi().toString()))
                .andExpect(jsonPath("$.pocetStringu").value(revizeExample0.getPocetStringu().toString()))
                .andExpect(jsonPath("$.jisteni").value(revizeExample0.getJisteni()))
                .andExpect(jsonPath("$.prepetovaOchrana").value(revizeExample0.getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$.fotkaSrc").value(revizeExample0.getFotkaSrc()));
    }

    @Test
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Revize revizeExampleUpdated = new Revize (
                (long)1,
                LocalDate.of(1234, Month.DECEMBER, 26),
                LocalDate.of(2015, Month.JANUARY, 12),
                LocalDate.of(2018, Month.JULY, 29),
                true,
                "updated",
                (short)6,
                (short)8,
                "asd",
                (short) 69,
                "dasgfdfgfgd",
                null,
                null
        );

        Mockito.when(revizeService.update(revizeExample0)).thenReturn(revizeExampleUpdated);

        mockMvc.perform(put("/revize/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(revizeExample0))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(revizeExampleUpdated.getId()))
                .andExpect(jsonPath("$.datumVypracovani").value(revizeExampleUpdated.getDatumVypracovani().toString()))
                .andExpect(jsonPath("$.datumUkonceniRevize").value(revizeExampleUpdated.getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$.datumPredaniRevize").value(revizeExampleUpdated.getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$.jeNovaInstalace").value(revizeExampleUpdated.getJeNovaInstalace()))
                .andExpect(jsonPath("$.distribucniSit").value(revizeExampleUpdated.getDistribucniSit()))
                .andExpect(jsonPath("$.pocetFazi").value(revizeExampleUpdated.getPocetFazi().toString()))
                .andExpect(jsonPath("$.pocetStringu").value(revizeExampleUpdated.getPocetStringu().toString()))
                .andExpect(jsonPath("$.jisteni").value(revizeExampleUpdated.getJisteni()))
                .andExpect(jsonPath("$.prepetovaOchrana").value(revizeExampleUpdated.getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$.fotkaSrc").value(revizeExampleUpdated.getFotkaSrc()));
    }

    @Test
    void getRevizeByDatumPredaniRevizeBetween() throws Exception {
        Revize revize1 = new Revize (
                (long) 2,
                LocalDate.of(2006, Month.DECEMBER, 29),
                LocalDate.of(2006, Month.DECEMBER, 29),
                LocalDate.of(2006, Month.DECEMBER, 29),
                true,
                "wef",
                (short)6,
                (short)8,
                "asd",
                (short) 69,
                "das",
                null,
                null
        );

        List<Revize> revize = new ArrayList<Revize>();
        revize.add(revizeExample0);
        revize.add(revize1);

        LocalDate datumPredaniRevizeOd = LocalDate.of(2005, Month.DECEMBER, 29);
        LocalDate datumPredaniRevizeDo = LocalDate.of(2010, Month.DECEMBER, 29);

        Mockito.when(revizeService.getRevizeByDatumPredaniRevizeBetween(datumPredaniRevizeOd, datumPredaniRevizeDo))
                .thenReturn(revize);

        mockMvc.perform(get("/revize/between_date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("datumPredaniRevizeOd", datumPredaniRevizeOd.toString())
                        .param("datumPredaniRevizeDo", datumPredaniRevizeDo.toString())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(revize.get(0).getId()))
                .andExpect(jsonPath("$[0].datumVypracovani").value(revize.get(0).getDatumVypracovani().toString()))
                .andExpect(jsonPath("$[0].datumUkonceniRevize").value(revize.get(0).getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$[0].datumPredaniRevize").value(revize.get(0).getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$[0].jeNovaInstalace").value(revize.get(0).getJeNovaInstalace()))
                .andExpect(jsonPath("$[0].distribucniSit").value(revize.get(0).getDistribucniSit()))
                .andExpect(jsonPath("$[0].pocetFazi").value(revize.get(0).getPocetFazi().toString()))
                .andExpect(jsonPath("$[0].pocetStringu").value(revize.get(0).getPocetStringu().toString()))
                .andExpect(jsonPath("$[0].jisteni").value(revize.get(0).getJisteni()))
                .andExpect(jsonPath("$[0].prepetovaOchrana").value(revize.get(0).getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$[0].fotkaSrc").value(revize.get(0).getFotkaSrc()))
                .andExpect(jsonPath("$[1].id").value(revize.get(1).getId()))
                .andExpect(jsonPath("$[1].datumVypracovani").value(revize.get(1).getDatumVypracovani().toString()))
                .andExpect(jsonPath("$[1].datumUkonceniRevize").value(revize.get(1).getDatumUkonceniRevize().toString()))
                .andExpect(jsonPath("$[1].datumPredaniRevize").value(revize.get(1).getDatumPredaniRevize().toString()))
                .andExpect(jsonPath("$[1].jeNovaInstalace").value(revize.get(1).getJeNovaInstalace()))
                .andExpect(jsonPath("$[1].distribucniSit").value(revize.get(1).getDistribucniSit()))
                .andExpect(jsonPath("$[1].pocetFazi").value(revize.get(1).getPocetFazi().toString()))
                .andExpect(jsonPath("$[1].pocetStringu").value(revize.get(1).getPocetStringu().toString()))
                .andExpect(jsonPath("$[1].jisteni").value(revize.get(1).getJisteni()))
                .andExpect(jsonPath("$[1].prepetovaOchrana").value(revize.get(1).getPrepetovaOchrana().toString()))
                .andExpect(jsonPath("$[1].fotkaSrc").value(revize.get(1).getFotkaSrc()));
    }
}