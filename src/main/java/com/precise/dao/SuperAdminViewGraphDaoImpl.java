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

import com.precise.model.Project;

@Repository
public class SuperAdminViewGraphDaoImpl implements SuperAdminViewGraphDao{

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

	
	@Override
	public List<Project> getthegraphofsubmodules(Project pro) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminviewgraphprogress(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getsumodulegraph");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, pro.getModuleId());
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			while (rs.next()) {
				Project pro1 = new Project();
				pro1.setSubModuleId(rs.getInt("pk_submod_id"));
				pro1.setPercentage(rs.getDouble("per"));
				pro1.setModuleNameIndividually(rs.getString("mod_name"));
				pro1.setSubModuleNameIndividually(rs.getString("submod_name"));
                listRole.add(pro1);
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

	@Override
	public List<Project> getthegraphofprojects(Project pro) {
		List<Project> listRole = new ArrayList<Project>();
		final String procedureCall = "{call proc_adminviewgraphprogress(?,?)}";
		Connection connection = null;
		try {
			connection = jdbctemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getprojectgraph");
			callableSt.setInt(2, pro.getProjectId());
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			while (rs.next()) {
				Project pro1 = new Project();
				pro1.setModuleId(rs.getInt("fk_module_id"));
				pro1.setModuleNameIndividually(rs.getString("mod_name"));
				pro1.setPercentage((rs.getDouble("per")));
                listRole.add(pro1);
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
