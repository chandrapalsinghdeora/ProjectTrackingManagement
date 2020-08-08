package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.Project;

public interface TeamLeaderAddSubModuleDao {

    public List<Project> getallprojects(int userid);
    public List<Project> getalltlprojects(int userid);
    public JSONArray getmodules(int projectid, int userid);
    public void savesubmodules(Project pro);
}
