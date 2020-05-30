package uahb.coursjava;

import uahb.coursjava.model.Service;
import uahb.coursjava.model.Specialite;
import uahb.coursjava.service.IService;
import uahb.coursjava.service.ISpecialite;
import uahb.coursjava.service.ServiceService;
import uahb.coursjava.service.SpecialiteService;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main2 {
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        IService iService = new ServiceService();
        List<Service> services = iService.findAll();
        String srvs = services.stream().
                map(s->"["+s.getId()+", "+s.getLibelle()+"]").
                collect(Collectors.joining(", "));
        List<Integer> ids = services.stream().
                map(s->s.getId()).
                collect(Collectors.toList());
        int service_id;
        do {
            System.out.println(srvs);
            System.out.println("Saisir l'id du service");
            service_id = sc.nextInt();
        }while (!ids.contains(service_id));


        //------------------------------------------
        ISpecialite iSpecialite = new SpecialiteService();
        List<Specialite> specialites = iSpecialite.findAll();
        String sps = specialites.stream().
                map(s->"["+s.getLibelle()+"]").
                collect(Collectors.joining(", "));

        List<String> idsps = specialites.stream().
                map(s->s.getLibelle()).
                collect(Collectors.toList());
        String libelle;
        sc.nextLine();
        do {

            System.out.println("Specialités existantes : "+sps);
            System.out.println("Donner le libelle du service");
            libelle = sc.nextLine();
        }while(idsps.contains(libelle));
        // faire l'insertion
        Service s = new Service();
        s.setId(service_id);
        Specialite sp = new Specialite(0, libelle);
        sp.setServive(s);
        sp = iSpecialite.save(sp);
        System.out.println("Saved specite : "+sp.getId()+", "+sp.getLibelle());
        specialites = iSpecialite.findAll();
        specialites.forEach(System.out::println);
    }
}
