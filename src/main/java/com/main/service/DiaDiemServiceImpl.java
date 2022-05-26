package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.DAO.DiaDiemDAO;
import com.main.entity.Dia_Diem;

@Service
public class DiaDiemServiceImpl implements DiaDiemService {
    @Autowired
    private DiaDiemDAO diaDiemDAO;

    @Transactional
    @Override
    public List<Dia_Diem> tatCaDiaDiem() {
        return diaDiemDAO.tatCaDiaDiem();
    }

    @Transactional
    @Override
    public Dia_Diem getDiaDiem(String tenDiaDiem) {
        // TODO Auto-generated method stub
        return diaDiemDAO.getDiaDiem(tenDiaDiem);
    }

    @Transactional
    @Override
    public void save(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.save(diaDiem);
    }

    @Transactional
    @Override
    public Dia_Diem getDiaDiem(int idDiaDiem) {
        // TODO Auto-generated method stub

        return diaDiemDAO.getDiaDiem(idDiaDiem);
    }

    @Transactional
    @Override
    public void delete(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.delete(diaDiem);
    }

    @Transactional
    @Override
    public void update(Dia_Diem diaDiem) {
        // TODO Auto-generated method stub
        diaDiemDAO.update(diaDiem);
    }

}
