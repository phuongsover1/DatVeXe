package com.main.service;

import java.util.List;

import com.main.entity.Dia_Diem;

public interface DiaDiemService {
    public List<Dia_Diem> tatCaDiaDiem();

    public Dia_Diem getDiaDiem(String tenDiaDiem);

    public void save(Dia_Diem diaDiem);

    public Dia_Diem getDiaDiem(int idDiaDiem);

    public void delete(Dia_Diem diaDiem);

    public void update(Dia_Diem diaDiem);
}
