package net.vnpt.tienhung.managerequipment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiendn on 04/08/2017.
 */

public class ListRecordsAdapter extends RecyclerView.Adapter<ListRecordsAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Equipment> mEquipments;
    ILisnter lisnter;

    public ListRecordsAdapter(Context mContext, ArrayList<Equipment> mEquipments, ILisnter lisnter) {
        this.mContext = mContext;
        this.mEquipments = mEquipments;
        this.lisnter = lisnter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setUpView(mEquipments.get(position));
    }

    @Override
    public int getItemCount() {
        return mEquipments.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_tram)
        TextView tvTram;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_part_number)
        TextView tvPartNumber;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lisnter.onClick(mEquipments.get(getAdapterPosition()));
                }
            });
        }

        public void setUpView(Equipment equipment){
            tvTram.setText(equipment.getTram().toUpperCase());
            tvName.setText(equipment.getThietBi());
            tvPartNumber.setText(equipment.getPartNumber());
        }
    }

    public interface ILisnter{
        void onClick (Equipment equipment);
    }
}
