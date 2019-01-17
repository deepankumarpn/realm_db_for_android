package dev.spooke.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.spooke.realmdb.DB.PersonRealmDb;
import io.realm.Realm;
import io.realm.RealmResults;

public class InsertActivity extends AppCompatActivity {
    Button btn_insert_data;
    EditText edt_email,edt_name,edt_id;
    String str_id_increment;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btn_insert_data=(Button)findViewById(R.id.btn_insert_data);
        edt_id=(EditText)findViewById(R.id.edt_id);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_name=(EditText)findViewById(R.id.edt_name);

        databaseInit();

        btn_insert_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRealm.beginTransaction();
                PersonRealmDb obj=mRealm.createObject(PersonRealmDb.class);
                obj.setId(Integer.parseInt(edt_id.getText().toString()));
                obj.setName(edt_name.getText().toString());
                obj.setEmail(edt_email.getText().toString());
                mRealm.commitTransaction();
                databaseInit();
                edt_id.setText(""+str_id_increment);
                edt_email.setText("");
                edt_name.setText("");
            }
        });
    }

    public void databaseInit(){
        Realm.init(this);
        mRealm=Realm.getDefaultInstance();
        RealmResults<PersonRealmDb> result=mRealm.where(PersonRealmDb.class).findAll();
        str_id_increment=Integer.toString(result.last().getId()+1);
        edt_id.setText(str_id_increment);
    }
}
