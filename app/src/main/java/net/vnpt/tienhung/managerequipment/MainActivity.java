package net.vnpt.tienhung.managerequipment;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Environment.DIRECTORY_DOCUMENTS;

public class MainActivity extends AppCompatActivity implements ListFileAdapter.Listener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;

    ListFileAdapter mListFileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        File documentDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS);
        ArrayList<File> listFile = (ArrayList<File>) getListFiles(documentDirectory);

        mListFileAdapter = new ListFileAdapter(listFile, this, this);
        rvFiles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvFiles.setAdapter(mListFileAdapter);
    }

    private List<File> getListFiles(File parentDir){
        List<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.addAll(Arrays.asList(parentDir.listFiles()));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".vnpt")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }

    @Override
    public void onClick(String filePath) {
        Intent intent = new Intent(MainActivity.this, ListRecordsActivity.class);
        intent.putExtra("FILE_PATH", filePath);
        startActivity(intent);
    }
}
