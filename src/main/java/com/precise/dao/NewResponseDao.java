package com.precise.dao;

import com.precise.model.Bug;

public interface NewResponseDao {
	public void sendtheresponse(Bug emp,String fname);
 public void closethebug(Bug emp,int bugno);
}
