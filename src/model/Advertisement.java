package model;
import java.io.Serializable;
import java.util.Date;


public class Advertisement implements Serializable {
    private String title;
    private String description;
    private AdvertisementCategory advertisementCategory;
    private Date createdDate;
    private User createdUser;
    private double price;
    private String address;
    private String contactPhone;


    public Advertisement(String title, String description, AdvertisementCategory advertisementCategory, Date createdDate,
                         User createdUser, double price, String address, String contactPhone) {
        this.title = title;
        this.description = description;
        this.advertisementCategory = advertisementCategory;
        this.createdDate = createdDate;
        this.createdUser = createdUser;
        this.price = price;
        this.address = address;
        this.contactPhone = contactPhone;
    }

    public Advertisement() {

    }

    public AdvertisementCategory getAdvertisementCategory() {
        return advertisementCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdvertisementCategory(AdvertisementCategory advertisementCategory) {
        this.advertisementCategory = advertisementCategory;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", advertisementCategory=" + advertisementCategory +
                ", createdDate=" + createdDate +
                ", createdUser=" + createdUser +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (advertisementCategory != that.advertisementCategory) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdUser != null ? !createdUser.equals(that.createdUser) : that.createdUser != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return contactPhone != null ? contactPhone.equals(that.contactPhone) : that.contactPhone == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (advertisementCategory != null ? advertisementCategory.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdUser != null ? createdUser.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        return result;
    }


}
