package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.DevResponseDao;
import com.precise.model.Bug;
@Service

public class DevResponseServiceImpl implements DevResponseService {
	@Autowired
	DevResponseDao devresponsedao;


    @Override
	public List<Bug> devresgettheinformation(int id) {
		return devresponsedao.devresgettheinformation(id);
	}
}
