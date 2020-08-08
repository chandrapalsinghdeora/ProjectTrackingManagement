
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
public class DevBugDaoImpl implements DevBugDao {

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

	
	public List<Bug> devgettheinformation(int id){
			List<Bug> listRole = new ArrayList<Bug>();
			final String procedureCall = "{call pro_BugTracking(?,?)}";
			Connection connection = null;
			try {
				connection = jdbctemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectbugentrydev");
				callableSt.setInt(2,id);
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					Bug b = new Bug();
	                b.setBugNo(rs.getInt("bugNo"));
	                b.setProjectName(rs.getString("projectname"));
	                b.setModule(rs.getString("mod_name"));
	                b.setBugName(rs.getString("bugName"));
	                b.setBugType(rs.getString("bugType"));
	                b.setBugSeverity(rs.getString("bugSeverity"));
	                b.setName(rs.getString("name"));
	                b.setDate(rs.getDate("date"));
	                b.setStatus(rs.getString("status"));
	                b.setBugStatus(rs.getString("bugStatus"));
	                b.setSummary(rs.getString("summary"));
	                listRole.add(b);
					
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
