package com.precise.dao;

import java.util.List;

import com.precise.model.Bug;

public interface IssueDao {
	public List<Bug> issueinformation(int bugNo);

	
}
