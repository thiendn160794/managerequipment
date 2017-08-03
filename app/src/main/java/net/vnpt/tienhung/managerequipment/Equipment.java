package net.vnpt.tienhung.managerequipment;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by thiendn on 02/08/2017.
 */

@RealmClass
public class Equipment implements RealmModel {
    @PrimaryKey
    String stt;
    String dai;
    String tram;
    String thietBi;
    String tenVatTu;
    String partNumber;
    String serial;
    String viTri;
    String ghiChu;
    String ngayCapNhat;
    String sdt;

    public Equipment() {
    }

    public Equipment(String stt, String dai, String tram, String thietBi, String tenVatTu, String partNumber, String serial, String viTri, String ghiChu, String ngayCapNhat, String sdt) {
        this.stt = stt;
        this.dai = dai;
        this.tram = tram;
        this.thietBi = thietBi;
        this.tenVatTu = tenVatTu;
        this.partNumber = partNumber;
        this.serial = serial;
        this.viTri = viTri;
        this.ghiChu = ghiChu;
        this.ngayCapNhat = ngayCapNhat;
        this.sdt = sdt;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getDai() {
        return dai;
    }

    public void setDai(String dai) {
        this.dai = dai;
    }

    public String getTram() {
        return tram;
    }

    public void setTram(String tram) {
        this.tram = tram;
    }

    public String getThietBi() {
        return thietBi;
    }

    public void setThietBi(String thietBi) {
        this.thietBi = thietBi;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public static Equipment fromJson(JSONObject jsonObject){
        Equipment equipment = new Equipment();
        try {
            equipment.setStt(jsonObject.getString("stt"));
            equipment.setDai(jsonObject.getString("dai"));
            equipment.setTram(jsonObject.getString("tram"));
            equipment.setThietBi(jsonObject.getString("thiet_bi"));
            equipment.setTenVatTu(jsonObject.getString("ten_vat_tu"));
            equipment.setPartNumber(jsonObject.getString("part_number"));
            equipment.setSerial(jsonObject.getString("serial"));
            equipment.setViTri(jsonObject.getString("vi_tri"));
            equipment.setGhiChu(jsonObject.getString("ghi_chu"));
            equipment.setNgayCapNhat(jsonObject.getString("ngay_cap_nhat"));
            equipment.setSdt(jsonObject.getString("sdt"));
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return equipment;
    }
}
