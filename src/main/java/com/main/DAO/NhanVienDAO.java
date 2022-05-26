package com.main.DAO;

import java.util.List;

import com.main.entity.Nhan_Vien;

public interface NhanVienDAO {

    public Nhan_Vien getNhanVienFromUsername(String username);

    public Nhan_Vien getNhanVienFromId(int idNhanVien);

    public void updateNV(Nhan_Vien nhanVien);

    public List<Nhan_Vien> getListNV();

    public void xoaNV(int nvID);

    public void themNV(Nhan_Vien nhanVien);
}
