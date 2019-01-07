package dev.spooke.realmdb.DB;

import io.realm.RealmObject;

public class PersonRealmDb extends RealmObject {

    int id;
    String Name;
    String Email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
