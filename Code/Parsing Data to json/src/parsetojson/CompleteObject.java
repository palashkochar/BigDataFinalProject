/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetojson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author palashkochar
 */
public class CompleteObject {

    private int Id;
    private String ASIN;
    private String title;
    private String group;
    private String salesrank;
    private List<String> similar = new ArrayList();
    private Reviews reviews = new Reviews();

    public CompleteObject() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getASIN() {
        return ASIN;
    }

    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(String salesrank) {
        this.salesrank = salesrank;
    }

    public List<String> getSimilar() {
        return similar;
    }

    public void setSimilar(List<String> similar) {
        this.similar = similar;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "CompleteObject{" + "Id=" + Id + ", ASIN=" + ASIN + ", title=" + title + ", group=" + group + ", salesrank=" + salesrank + ", similar=" + similar + ", reviews=" + reviews + '}';
    }
}
