package com.main.service;

import com.main.entity.Xe;

public interface XeService {
	public Xe getXe(int idXe);

	public Xe layXeTuBienSoXe(String bienSoXe);

	public void save(Xe xeMoi);
}
