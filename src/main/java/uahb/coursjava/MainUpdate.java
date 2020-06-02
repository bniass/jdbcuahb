package uahb.coursjava;

import uahb.coursjava.model.Specialite;
import uahb.coursjava.service.ISpecialite;
import uahb.coursjava.service.SpecialiteService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainUpdate {
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        ISpecialite iSpecialite = new SpecialiteService();
        List<Specialite> specialites = iSpecialite.findAll();
        String specialityNames = specialites.stream().
                map(s->s.getLibelle()).
                collect(Collectors.joining(", "));

        List<String> idsps = specialites.stream().
                map(s->s.getLibelle()).
                collect(Collectors.toList());
        String libelle;
        do {

            System.out.println("Specialités existantes : "+specialityNames);
            System.out.println("Donner le libelle de la spécialité");
            libelle = sc.nextLine();
        }while(!idsps.contains(libelle));
        final String lib = libelle;
        System.out.println("Donner le nouveau nom du spécialité");
        String newLibelle = sc.nextLine();
        Specialite sp = specialites.stream().filter(s->s.getLibelle()
                .equalsIgnoreCase(lib)).findFirst().get();
        try {
            sp.setLibelle(newLibelle);
            iSpecialite.update(sp);
            System.out.println("Specialité modifiée !");
            specialites = iSpecialite.findAll();
            specialites.forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Erreur :"+e);
        }
    }
}
