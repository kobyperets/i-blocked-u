package com.salesforce.iblockedu.IBlockedU.model;

/**
 * Created by doron.levi on 29/11/2017.
 */
public class User extends BaseEntity{
    private String email;
    private String name;
    private String phoneNumber;
    private String imageLocation;
    private boolean active;
    private static User empty = new User(-1,null,null,null,null,false);

    public User(int id, String email, String name, String phoneNumber, String imageLocation, boolean active) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageLocation = imageLocation;
        this.active = active;
    }

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static User getEmpty() {
        return empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if  (id == user.id) {
            if (email == user.email)
                return true;
            else {
                if (email == null)
                    return false;

                if (user.email == null)
                    return false;

                if (email.toLowerCase().equals(user.email.toLowerCase()))
                    return true;
            }

        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (imageLocation != null ? imageLocation.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
