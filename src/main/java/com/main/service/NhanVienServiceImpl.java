package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.NhanVienDAO;
import com.main.entity.Nhan_Vien;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienDAO nhanVienDAO;

    @Transactional
    @Override
    public Nhan_Vien getNhanVienFromUsername(String username) {
        return nhanVienDAO.getNhanVienFromUsername(username);
    }

    @Transactional
    @Override
    public Nhan_Vien getNhanVienFromId(int idNhanVien) {
        return nhanVienDAO.getNhanVienFromId(idNhanVien);
    }

    @Transactional
    @Override
    public void updateNV(Nhan_Vien tempNV) {
        nhanVienDAO.updateNV(tempNV);

    }

    @Transactional
    @Override
    public List<Nhan_Vien> getListNV() {
        // TODO Auto-generated method stub
        return nhanVienDAO.getListNV();
    }

    @Transactional
    @Override
    public void xoaNV(int nvID) {
        // TODO Auto-generated method stub
        nhanVienDAO.xoaNV(nvID);
    }

    @Transactional
    @Override
    public void themNV(Nhan_Vien nhanVien) {
        // TODO Auto-generated method stub
        nhanVienDAO.themNV(nhanVien);
    }

}
