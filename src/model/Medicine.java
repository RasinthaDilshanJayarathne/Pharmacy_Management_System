package model;

public class Medicine {
    private String id;
    private String name;
    private String pack;
    private int qty;
    private String madeDate;
    private String expDate;
    private String company;

    public Medicine() {
    }

    public Medicine(String id, String name, String pack, int qty, String madeDate, String expDate, String company) {
        this.setId(id);
        this.setName(name);
        this.setPack(pack);
        this.setQty(qty);
        this.setMadeDate(madeDate);
        this.setExpDate(expDate);
        this.setCompany(company);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pack='" + pack + '\'' +
                ", qty=" + qty +
                ", madeDate='" + madeDate + '\'' +
                ", expDate='" + expDate + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
