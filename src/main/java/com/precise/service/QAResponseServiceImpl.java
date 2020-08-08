package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.QAResponseDao;
import com.precise.model.Bug;
@Service

public class QAResponseServiceImpl implements QAResponseService {
	@Autowired
	QAResponseDao qaresponsedao;


    @Override
	public List<Bug> qaresgettheinformation(int id) {
		return qaresponsedao.qaresgettheinformation(id);
	}
}
