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
public class Reviews {

    private int total;
    private int downloaded;
    private float avgRating;
    private List<Customers> customers = new ArrayList<>();

    public Reviews() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public List<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Reviews{" + "total=" + total + ", downloaded=" + downloaded + ", avgRating=" + avgRating + ", customers=" + customers + '}';
    }
}
