package school.hei.patrimoine.modele;

import org.junit.jupiter.api.Test;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.util.Set;

import static java.time.Month.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatrimoineZetyTest {

    @Test
    void patrimoine_zety_au_17_septembre_2024() {
        var zety = new Personne("Zety");

        var au3juillet24 = LocalDate.of(2024, JULY, 3);
        var au17septembre24 = LocalDate.of(2024, SEPTEMBER, 17);

        var ordinateur = new Materiel("Ordinateur", au3juillet24, 1_200_000,au3juillet24, -0.0996);
        var vetements = new Materiel("Vêtements", au3juillet24, 1_500_000,au3juillet24, -0.4988);
        var espece = new Argent("Espèces", au3juillet24, 800_000);
        var compteBancaire = new Argent("Compte bancaire", au3juillet24, 100_000);
        var fraisTenueCompte = new FluxArgent(
                "Frais tenue de compte",
                compteBancaire, au3juillet24, au17septembre24, -20_000, 25);
        var fraisScolarite = new FluxArgent(
                "Frais de scolarité",
                espece, LocalDate.of(2023, NOVEMBER, 27), LocalDate.of(2024, AUGUST, 27), -200_000, 30);

        var patrimoineZetyAu17septembre24 = new Patrimoine(
                "patrimoineZetyAu17septembre24",
                zety,
                au3juillet24,
                Set.of(ordinateur, vetements, espece, compteBancaire, fraisTenueCompte, fraisScolarite));

        assertEquals(3_179_323, patrimoineZetyAu17septembre24.projectionFuture(au17septembre24).getValeurComptable());
    }
}
