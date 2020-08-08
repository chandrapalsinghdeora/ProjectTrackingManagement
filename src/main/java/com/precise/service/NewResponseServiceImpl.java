package com.precise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.NewResponseDao;
import com.precise.model.Bug;

@Service
public class NewResponseServiceImpl implements NewResponseService {
	@Autowired
	NewResponseDao newresponsedao;

	@Override
	public void sendtheresponse(Bug emp, String fname) {

		newresponsedao.sendtheresponse(emp,fname);
	}


	@Override
	public void closethebug(Bug emp, int bugno) {
		
		newresponsedao.closethebug(emp,bugno);
	}


    
}
