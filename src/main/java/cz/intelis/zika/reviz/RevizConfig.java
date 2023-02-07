package cz.intelis.zika.reviz;

import cz.intelis.zika.reviz.objednatele.Objednatele;
import cz.intelis.zika.reviz.objednatele.ObjednateleRepository;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjekty;
import cz.intelis.zika.reviz.revidovane_objekty.RevidovaneObjektyRepository;
import cz.intelis.zika.reviz.revize.Revize;
import cz.intelis.zika.reviz.revize.RevizeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

@Configuration
public class RevizConfig {
    @Bean
    CommandLineRunner commandLineRunner(RevizeRepository revizeRepository, ObjednateleRepository objednateleRepository, RevidovaneObjektyRepository revidovaneObjektyRepository) {
        return args -> {
            int count = 6;
            for(int i = 0; i < count; i++) {
                Revize revize = new Revize (
                        LocalDate.of(2005 + i, Month.DECEMBER, 29),
                        LocalDate.of(2005 + i, Month.DECEMBER, 29),
                        LocalDate.of(2005 + i, Month.DECEMBER, 29),
                        true,
                        getRandomString(5),
                        (short)6,
                        (short)8,
                        getRandomString(3),
                        (short) 69,
                        getRandomString(4)
                );

                revizeRepository.save(revize);
            }

            int count0 = 9;
            for(int i0 = 0; i0 < count0; i0++) {
                Objednatele objednatele = new Objednatele (
                        getRandomString(8),
                        getRandomString(2),
                        getRandomString(5),
                        getRandomString(6),
                        getRandomString(12)
                );

                objednateleRepository.save(objednatele);
            }

            int count1 = 9;
            for(int i1 = 0; i1 < count1; i1++) {
                RevidovaneObjekty revidovaneObjekty = new RevidovaneObjekty (
                        getRandomString(2),
                        getRandomString(5),
                        getRandomString(6),
                        getRandomString(8),
                        false
                );

                revidovaneObjektyRepository.save(revidovaneObjekty);
            }
        };
    }

    public String getRandomString(int size) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
