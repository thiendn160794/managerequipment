package net.vnpt.tienhung.managerequipment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListRecordsActivity extends AppCompatActivity {

    String mFilePath = "";
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        mFilePath = getIntent().getExtras().getString("FILE_PATH");
        File file = new File(mFilePath);
        final String fileContent = readFile(file);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateAllFromJson(Equipment.class, fileContent);
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Equipment> a = realm.where(Equipment.class).findAll();
                System.out.println("thiendn: size of list: " + a.size());
            }
        });
    }

    private String readFile(File file){
        String myData = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myData;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
