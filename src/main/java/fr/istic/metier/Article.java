package fr.istic.metier;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.Collection;

@Entity("articles")
public class Article {

    @Id
    private ObjectId id;
    private String name;
    private Integer stars;
    @Reference
    private Collection<Person> buyers;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Collection<Person> getBuyers() {
        return buyers;
    }

    public void setBuyers(Collection<Person> buyers) {
        this.buyers = buyers;
    }
}
