package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.DevBugDao;
import com.precise.model.Bug;
@Service

public class DevBugServiceImpl implements DevBugService {
	@Autowired
	DevBugDao devbugdao;


    @Override
	public List<Bug> devgettheinformation(int id) {
		return devbugdao.devgettheinformation(id);
	}
}
