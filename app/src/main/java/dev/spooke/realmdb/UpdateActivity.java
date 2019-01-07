package dev.spooke.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.spooke.realmdb.DB.PersonRealmDb;
import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {
    Button btn_update_data;
    EditText edt_email_update,edt_name_update,edt_id_update;
    Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btn_update_data=(Button)findViewById(R.id.btn_update_data);
        edt_email_update=(EditText)findViewById(R.id.edt_email_update);
        edt_name_update=(EditText)findViewById(R.id.edt_name_update);
        edt_id_update=(EditText)findViewById(R.id.edt_id_update);

        Realm.init(this);
        mRealm=Realm.getDefaultInstance();

        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonRealmDb toEdit = mRealm.where(PersonRealmDb.class)
                        .equalTo("id",Integer.parseInt(edt_id_update.getText().toString())).findFirst();
                mRealm.beginTransaction();
                toEdit.setId(Integer.parseInt(edt_id_update.getText().toString()));
                toEdit.setName(edt_name_update.getText().toString());
                toEdit.setEmail(edt_email_update.getText().toString());
                mRealm.commitTransaction();

            }

//            Object obj = new Object();
//            obj.setField1(field1);
//            obj.setField2(field2);
//            realm.beginTransaction();
//            realm.copyToRealmOrUpdate(obj);
//            realm.commitTransaction();

        });
    }
}
