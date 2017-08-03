package net.vnpt.tienhung.managerequipment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiendn on 01/08/2017.
 */

public class ListFileAdapter extends RecyclerView.Adapter<ListFileAdapter.ViewHolder> {

    ArrayList<File> mListFile;
    Context mContext;
    Listener listener;

    public ListFileAdapter(ArrayList<File> mListFile, Context mContext, Listener listener) {
        this.mListFile = mListFile;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File file = mListFile.get(position);
        holder.tvFileName.setText(file.getName());
        holder.tvFileSize.setText(file.length()/1024 + " KB");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        holder.tvFileDayModified.setText(sdf.format(file.lastModified()));
    }

    @Override
    public int getItemCount() {
        return mListFile.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_file_name)
        TextView tvFileName;
        @BindView(R.id.tv_file_size)
        TextView tvFileSize;
        @BindView(R.id.tv_file_day_modified)
        TextView tvFileDayModified;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File file = mListFile.get(getAdapterPosition());
                    System.out.println("con cac: " + file.getAbsolutePath());
                    try {
                        System.out.println("con cac: " + file.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("con cac: " + file.getPath());
                    System.out.println("con cac: " + file.canRead());
                    listener.onClick(file.getAbsolutePath());
                }
            });
        }
    }

    public interface Listener{
        void onClick(String filePath);
    }
}
