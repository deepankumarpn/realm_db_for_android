package dev.spooke.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dev.spooke.realmdb.DB.PersonRealmDb;
import io.realm.Realm;
import io.realm.RealmResults;

public class ViewActivity extends AppCompatActivity {

    TextView txt_view_display;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        txt_view_display=(TextView)findViewById(R.id.txt_view_display);

        Realm.init(this);
        mRealm=Realm.getDefaultInstance();

        RealmResults<PersonRealmDb> result=mRealm.where(PersonRealmDb.class).findAll();
        for(int i=0;i<result.size();i++){
            txt_view_display.append(" ID:"+result.get(i).getId()+"\n Name:"+result.get(i).getName()+
                    "\n Email:"+result.get(i).getEmail()+"\n");
        }
    }
}
