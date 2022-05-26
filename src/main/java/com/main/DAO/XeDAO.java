package com.main.DAO;

import com.main.entity.Xe;

public interface XeDAO {
	public Xe getXe(int idXe);

	public Xe getXeTuBienSo(String bienSoXe);

	public void save(Xe xeMoi);
}
