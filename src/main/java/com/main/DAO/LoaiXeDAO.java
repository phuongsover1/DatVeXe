package com.main.DAO;

import java.util.List;

import com.main.entity.Loai_Xe;

public interface LoaiXeDAO {
	public List<Loai_Xe> getList();

	public Loai_Xe getLoaiXeFromId(int idLoaiXe);
}
