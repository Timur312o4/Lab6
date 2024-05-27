package Network;
import City.City;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;


public class Response implements Serializable {
    private String typeResponse="";
    private String responce ="";
    private Collection<City> collection;
    public Response(String typeResponse, String responce,Collection<City> collection) {
        this.typeResponse = typeResponse;
        this.responce = responce;
        this.collection = collection.stream().sorted(Comparator
                .comparing(City::getPopulation)).toList();
    }
    public Response(String typeResponse, String responce){
        this.typeResponse = typeResponse;
        this.responce = responce;
    }
    public String getResponse() {
        return responce;
    }
    public Collection<City> getCollection() {
        return collection;
    }
    public String getTypeResponse() {
        return typeResponse;
    }
}
