package dev.spooke.realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.spooke.realmdb.DB.PersonRealmDb;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DeleteActivity extends AppCompatActivity {

    Button btn_delete_data,btn_delete_row_data;
    EditText edt_email_delete,edt_name_delete,edt_id_delete;
    Realm mRealm;

    int int_edt_id_delete;
    String str_edt_email_delete="",str_edt_name_delete="",str_edt_id_delete="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        btn_delete_data=(Button)findViewById(R.id.btn_delete_data);
        btn_delete_row_data=(Button)findViewById(R.id.btn_delete_row_data);

        edt_email_delete=(EditText)findViewById(R.id.edt_email_delete);
        edt_name_delete=(EditText)findViewById(R.id.edt_name_delete);
        edt_id_delete=(EditText)findViewById(R.id.edt_id_delete);


        Realm.init(this);
        mRealm=Realm.getDefaultInstance();

        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmQuery<PersonRealmDb> query = mRealm.where(PersonRealmDb.class);
                mRealm.beginTransaction();
                RealmResults<PersonRealmDb> skuItems1 = query.findAll();

                if (skuItems1 != null) {
                    skuItems1.deleteAllFromRealm();
                    mRealm.commitTransaction();
                    mRealm.refresh();
                }
            }
        });

        btn_delete_row_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_edt_email_delete = edt_email_delete.getText().toString().trim();
                str_edt_name_delete = edt_name_delete.getText().toString().trim();
                str_edt_id_delete= edt_id_delete.getText().toString().trim();


                /*Delete Particular ROW value using ID Field*/
                if(str_edt_id_delete.isEmpty() || str_edt_id_delete.length() == 0 || str_edt_id_delete.equals("") || str_edt_id_delete == null)
                {
                    //EditText is empty
                    /*Delete Particular ROW value using Name Field*/
                    if(str_edt_name_delete.isEmpty() || str_edt_name_delete.length() == 0 || str_edt_name_delete.equals("") || str_edt_name_delete == null)
                    {
                        //EditText is empty
                        /*Delete Particular ROW value using Email Field*/
                        if(str_edt_email_delete.isEmpty() || str_edt_email_delete.length() == 0 || str_edt_email_delete.equals("") || str_edt_email_delete == null)
                        {
                            //EditText is empty
                            Toast.makeText(DeleteActivity.this, "All the fields are Empty..", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            //EditText is not empty
                            PersonRealmDb toDeleteEmail = mRealm.where(PersonRealmDb.class).
                                    equalTo("Email",str_edt_email_delete).findFirst();
                            if (toDeleteEmail != null) {
                                mRealm.beginTransaction();
                                toDeleteEmail.deleteFromRealm();
                                mRealm.commitTransaction();
                            }
                            else{
                                Toast.makeText(DeleteActivity.this, "No Email is Found!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else
                    {
                        //EditText is not empty
                        PersonRealmDb toDeleteName = mRealm.where(PersonRealmDb.class).
                                equalTo("Name",str_edt_name_delete).findFirst();
                        if (toDeleteName != null) {
                            mRealm.beginTransaction();
                            toDeleteName.deleteFromRealm();
                            mRealm.commitTransaction();
                        }
                        else{
                            Toast.makeText(DeleteActivity.this, "No Name is Found!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {

                    int_edt_id_delete=Integer.parseInt(str_edt_id_delete);
                    //EditText is not empty
                    PersonRealmDb toDeleteID = mRealm.where(PersonRealmDb.class).
                            equalTo("id",int_edt_id_delete).findFirst();
                    if (toDeleteID != null) {
                        mRealm.beginTransaction();
                        toDeleteID.deleteFromRealm();
                        mRealm.commitTransaction();
                    }
                    else{
                        Toast.makeText(DeleteActivity.this, "No ID is Found!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
