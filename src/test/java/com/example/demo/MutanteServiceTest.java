package com.example.demo;

import com.example.demo.Entidades.Mutantes;
import com.example.demo.Repository.MutantesRepository;
import com.example.demo.services.MutanteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MutanteServiceTest {

    @Autowired
    private MutanteService mutanteService;

    @Autowired
    private MutantesRepository mutantesRepository;

    private Mutantes mutante;

    @BeforeEach
    public void setUp() {
        mutante = new Mutantes();
        mutante.setAdn(new String[]{"ATCG", "ATCG", "ATCG", "ATCG"});
        mutante.setEsMutante(true);
        mutantesRepository.save(mutante); // Guardar el mutante antes de cada prueba
    }

    @AfterEach
    public void tearDown() {
        mutantesRepository.deleteAll(); // Limpiar la base de datos después de cada prueba
    }

    @Test
    public void testSaveMutante() throws Exception {
        // Verifica que un mutante se guarda correctamente
        Mutantes savedMutante = mutanteService.save(mutante);
        assertThat(savedMutante).isNotNull();
        assertThat(savedMutante.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() throws Exception {
        // Verifica que se puede encontrar un mutante por su ID
        Mutantes foundMutante = mutanteService.findById(mutante.getId());
        assertThat(foundMutante).isNotNull();
        assertThat(foundMutante.getId()).isEqualTo(mutante.getId());
    }

    @Test
    public void testDeleteMutante() throws Exception {
        // Verifica que un mutante se elimina correctamente
        Long mutanteId = mutante.getId();
        mutanteService.delete(mutanteId);
        assertThat(mutantesRepository.findById(mutanteId)).isEmpty();
    }

    @Test
    public void testIsMutant() {
        // Verifica que el ADN corresponde a un mutante
        boolean result = mutanteService.isMutant(mutante.getAdn());
        assertThat(result).isTrue();
    }

    // Tests adicionales para validar secuencias de ADN
    @Test
    public void testRows() {
        // Verifica que se detecta un mutante en las filas
        String[] dna = {
                "AAAATG",
                "TGCAGT",
                "GCTTCC",
                "CCCCTG",
                "GTAGTC",
                "AGTCAC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testColumns() {
        // Verifica que se detecta un mutante en las columnas
        String[] dna = {
                "AGAATG",
                "TGCAGT",
                "GCTTCC",
                "GTCCTC",
                "GTAGTC",
                "GGTCAC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testMainDiagonals() {
        // Verifica que se detecta un mutante en las diagonales principales
        String[] dna = {
                "AGAATG",
                "TACAGT",
                "GCAGCC",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testSecondaryLeftDiagonals() {
        // Verifica que se detecta un mutante en las diagonales secundarias (izquierda)
        String[] dna = {
                "ATAATG",
                "GTTAGT",
                "GGCTCG",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testSecondaryRightDiagonals() {
        // Verifica que se detecta un mutante en las diagonales secundarias (derecha)
        String[] dna = {
                "ATAATG",
                "GTATGA",
                "GCTTAG",
                "TTTAGG",
                "GTAGTC",
                "AGTCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testTertiaryLeftDiagonals() {
        // Verifica que se detecta un mutante en las terceras diagonales (izquierda)
        String[] dna = {
                "ATGATG",
                "GTAGTA",
                "CCTTGG",
                "TCTAGG",
                "GGCGTC",
                "AGTCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testTertiaryRightDiagonals() {
        // Verifica que se detecta un mutante en las terceras diagonales (derecha)
        String[] dna = {
                "ATGATG",
                "GTATTA",
                "AATTGG",
                "ACTAGT",
                "GGAGTC",
                "AGGCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testNonMutant() {
        // Verifica que el ADN no corresponde a un mutante
        String[] dna = {
                "ATGATG",
                "GTCTTA",
                "AATTGG",
                "ACTAGT",
                "GGATTC",
                "AGGCAA"
        };
        assertThat(mutanteService.isMutant(dna)).isFalse();
    }

    @Test
    public void testMutant1() {
        // Verifica que un caso específico de ADN corresponde a un mutante
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testNonMutant1() {
        // Verifica que un caso específico de ADN no corresponde a un mutante
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertThat(mutanteService.isMutant(dna)).isFalse();
    }

    @Test
    public void testMutant2() {
        // Verifica que un caso específico de ADN corresponde a un mutante
        String[] dna = {
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testMutant3() {
        // Verifica que un caso de ADN totalmente igual corresponde a un mutante
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testNonMutant2() {
        // Verifica que un caso específico de ADN no corresponde a un mutante
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAG",
                "GGTC"
        };
        assertThat(mutanteService.isMutant(dna)).isFalse();
    }

    @Test
    public void testMutant4() {
        // Verifica que un caso específico de ADN corresponde a un mutante
        String[] dna = {
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTACG",
                "ACGAGTGCT",
                "AGACACATG",
                "GAATTCCAA",
                "ACTACGACC",
                "TGAGTATCC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }

    @Test
    public void testMutant5() {
        // Verifica que un caso específico de ADN corresponde a un mutante
        String[] dna = {
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "TTTTTTTTT",
                "CCGACCAGT",
                "GGCACTCCA",
                "AGGACACTA",
                "CAAAGGCAT",
                "GCAGTCCCC"
        };
        assertThat(mutanteService.isMutant(dna)).isTrue();
    }
}
