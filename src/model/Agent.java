package model;

public class Agent {
    private String Aid;
    private String name;
    private int age;
    private String contact;
    private String gender;

    public Agent() {
    }

    public Agent(String aid, String name, int age, String contact, String gender) {
        setAid(aid);
        this.setName(name);
        this.setAge(age);
        this.setContact(contact);
        this.setGender(gender);
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "Aid='" + Aid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
