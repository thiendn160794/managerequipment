package net.vnpt.tienhung.managerequipment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListRecordsActivity extends AppCompatActivity implements ListRecordsAdapter.ILisnter{

    @BindView(R.id.rv_list_records)
    RecyclerView rvListRecords;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String mFilePath = "";
    Realm realm;
    ArrayList<Equipment> equipmentArrayList;
    ListRecordsAdapter mListRecordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_records);
        ButterKnife.bind(this);

        toolbar.setTitle("Equipment Manager");
        setSupportActionBar(toolbar);
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        mFilePath = getIntent().getExtras().getString("FILE_PATH");
        File file = new File(mFilePath);
        final String fileContent = readFile(file);
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createOrUpdateAllFromJson(Equipment.class, fileContent);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Invalid file!! Can not open!", Toast.LENGTH_LONG).show();
            onBackPressed();
        }


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Equipment> a = realm.where(Equipment.class).findAll();
                equipmentArrayList = new ArrayList<Equipment>();
                for (Equipment equipment: a){
                    equipmentArrayList.add(equipment);
                }
            }
        });

        mListRecordsAdapter = new ListRecordsAdapter(this, equipmentArrayList, this);
        rvListRecords.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        rvListRecords.setAdapter(mListRecordsAdapter);
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

    @Override
    public void onClick(Equipment equipment) {
        Toast.makeText(this, "Clicked on equipment: " + equipment.getThietBi(), Toast.LENGTH_SHORT).show();
    }
}
