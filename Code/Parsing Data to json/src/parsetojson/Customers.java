/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetojson;

/**
 *
 * @author palashkochar
 */
public class Customers {
    private int customerid;
    private int rating;
    private int votes;
    private int helpful;

    public Customers() {
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerid=" + customerid + ", rating=" + rating + ", votes=" + votes + ", helpful=" + helpful + '}';
    }    
}
