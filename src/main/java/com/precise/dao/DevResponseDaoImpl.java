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
public class DevResponseDaoImpl implements DevResponseDao {

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


	public List<Bug> devresgettheinformation(int id)
	{
		{
				List<Bug> listRole = new ArrayList<Bug>();
				final String procedureCall = "{call pro_BugTracking(?,?)}";
				Connection connection = null;
				try {
					connection = jdbctemplate.getDataSource().getConnection();
					CallableStatement callableSt = connection.prepareCall(procedureCall);
					callableSt.setString(1, "selectbugentrydevres");
					callableSt.setInt(2, id);
					ResultSet rs = null;
					rs = callableSt.executeQuery();
					System.out.println("vale of rs is::"+rs.getFetchSize());
					int x=1;
					while (rs.next()) {
						Bug emp = new Bug();
			            emp.setBugNo(rs.getInt("bugNo"));
	                    emp.setProjectName(rs.getString("projectname"));;
	                    emp.setBugName(rs.getString("bugName"));
	                    emp.setName(rs.getString("name"));
	                    emp.setStatus(rs.getString("status"));
	                    emp.setSummary(rs.getString("summary"));
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
}
	
	
	
