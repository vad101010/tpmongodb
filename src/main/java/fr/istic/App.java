package fr.istic;

import fr.istic.metier.*;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "my_database");

        Article a1 = new Article();
        a1.setName("Découvrez comment gagner 10000€ par jour !");
        a1.setStars(5);
        Article a2 = new Article();
        a2.setName("Les scientifiques le détestent ! Comment perdre 20kg en 2 semaines");
        a2.setStars(4);
        Collection<Person> colP1 = new ArrayList<>();
        Collection<Person> colP2 = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            Person p = new Person();
            p.setName("Dupont" + i);
            Address address = new Address();
            address.setStreet(i + " Rue de Dupont");
            address.setCity("Duponcity");
            address.setPostCode("0000");
            address.setCountry("Duponland");
            //set address
            p.setAddress(address);
            // Save the POJO
            ds.save(p);
            if (i % 2 == 0) {
                colP1.add(p);
            } else {
                colP2.add(p);
            }
        }

        a1.setBuyers(colP1);
        a2.setBuyers(colP2);

        ds.save(a1);
        ds.save(a2);

        for (Article a : ds.find(Article.class)) {
            System.out.println(a.getName() + " a été acheté par :");
            for (Person p : a.getBuyers()) {
                System.out.println(p.getName() + " - " + p.getAddress().getStreet() + " " + p.getAddress().getCity());
            }
        }

    }
}
