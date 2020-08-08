package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Bug;

@Repository
public class IssueDaoImpl implements IssueDao {

	@Autowired
	private NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	@Autowired
	private JdbcTemplate jdbctemplate;

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	public NamedParameterJdbcTemplate getNamedparameterjdbctemplate() {
		return namedparameterjdbctemplate;
	}

	 public void setNamedparameterjdbctemplate(NamedParameterJdbcTemplate namedparameterjdbctemplate) {
		this.namedparameterjdbctemplate = namedparameterjdbctemplate;
	}

	
	public List<Bug> issueinformation(int bugNo){
	List<Bug> listRole = new ArrayList<Bug>();
	final String procedureCall = "{call pro_BugTracking(?,?)}";
	Connection connection = null;
	try {
		connection = jdbctemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "selectbugno");
		callableSt.setInt(2,bugNo);
		ResultSet rs = null;
		rs = callableSt.executeQuery();
		int x=1;
		while (rs.next()) {
			Bug emp = new Bug();
			emp.setQAName(rs.getString("qa_name"));
			System.out.println(rs.getString("qa_name"));
			emp.setMonitor(rs.getString("name"));
            emp.setAssignTo(rs.getString("assign_to"));
            emp.setModule(rs.getString("mod_name"));
            emp.setSubModule(rs.getString("submod_name"));
            emp.setRound(rs.getString("round"));
            emp.setDescription(rs.getString("description"));
            emp.setDepends(rs.getString("depends"));
            emp.setDate(rs.getDate("date"));
            listRole.add(emp);
			
			x=x+1;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	return listRole;
}


}
		
	
	

	
	
	
