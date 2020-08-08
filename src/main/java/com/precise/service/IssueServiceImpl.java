package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.IssueDao;
import com.precise.model.Bug;
@Service

public class IssueServiceImpl implements IssueService {
	@Autowired
	IssueDao issuedao;


    @Override
    public List<Bug> issueinformation(int bugNo) {
		return issuedao.issueinformation(bugNo);
	}
}
