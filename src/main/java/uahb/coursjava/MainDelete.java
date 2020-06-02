package uahb.coursjava;

import uahb.coursjava.model.Specialite;
import uahb.coursjava.service.ISpecialite;
import uahb.coursjava.service.SpecialiteService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainDelete {
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

        Specialite sp = specialites.stream().filter(s->s.getLibelle()
                .equalsIgnoreCase(lib)).findFirst().get();
        try {
            iSpecialite.delete(sp.getId());
            System.out.println("Specialité supprimé !");
            specialites = iSpecialite.findAll();
            specialites.forEach(System.out::println);
        }catch (Exception e){
            System.out.println("Erreur :"+e);
        }
    }
}
