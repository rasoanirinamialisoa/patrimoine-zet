package school.hei.patrimoine.modele;

import school.hei.patrimoine.modele.possession.Possession;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public record Patrimoine(
        String nom, Personne possesseur, LocalDate t, Set<Possession> possessions)
        implements Serializable {

  public int getValeurComptable() {
    return possessions.stream().mapToInt(Possession::getValeurComptable).sum();
  }

  public int getValeurComptableEnDevise(String codeDevise, double tauxChange, double tauxAppreciationAnnuel) {
    int total = 0;
    for (Possession possession : possessions) {
      total += possession.getValeurComptableEnDevise(codeDevise, tauxChange, tauxAppreciationAnnuel);
    }
    return total;
  }

  public Patrimoine projectionFuture(LocalDate tFutur) {
    return new Patrimoine(
            nom,
            possesseur,
            tFutur,
            possessions.stream().map(p -> p.projectionFuture(tFutur)).collect(toSet()));
  }

  public Possession possessionParNom(String nom) {
    return possessions.stream().filter(p -> nom.equals(p.getNom())).findFirst().orElseThrow();
  }
}
