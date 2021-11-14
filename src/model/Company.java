package model;

public class Company {
    private String Cid;
    private String name;
    private String address;
    private String contact;

    public Company() {
    }

    public Company(String cid, String name, String address, String contact) {
        setCid(cid);
        this.setName(name);
        this.setAddress(address);
        this.setContact(contact);
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CompanyTM{" +
                "Cid='" + Cid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
