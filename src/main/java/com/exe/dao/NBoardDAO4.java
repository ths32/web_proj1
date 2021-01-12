package com.exe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.exe.dto.NBoardDTO4;

public class NBoardDAO4 {
	
	private JdbcTemplate jdbcTemplate; //modified
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws Exception{ //modified
		this.jdbcTemplate = jdbcTemplate; //modified
	}
	
	public int getMaxNum4(){
		
		int maxNum4 = 0;
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select nvl(max(num4),0) from nboard4");
		maxNum4 = jdbcTemplate.queryForInt(sbuilder.toString());
		return maxNum4;
		
	}
	
	public void insertData4(NBoardDTO4 dto4){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("insert into nboard4 (num4,name4,subject4,created4,hitCount4,content4) values(?,?,?,sysdate,0,?)");
		jdbcTemplate.update(sbuilder.toString(),dto4.getNum4(),dto4.getName4(),dto4.getSubject4(),dto4.getContent4());
	}
	
	
	public List<NBoardDTO4> getList4(int start4, int end4, String searchKey4, String searchValue4){
		
			StringBuilder sbuilder = new StringBuilder();
			searchValue4 = "%" + searchValue4 + "%";
			sbuilder.append("select * from (");
			sbuilder.append("select rownum rnum,data.* from(");
			sbuilder.append("select num4,name4,subject4,to_char(created4,'YYYY-MM-DD') created4,hitCount4 ");
			sbuilder.append("from nboard4 where " + searchKey4 + " like ? order by num4 desc) data) ");
			sbuilder.append("where rnum >= ? and rnum <= ?");
			
			List<NBoardDTO4> lists4 = jdbcTemplate.query(sbuilder.toString(),new Object[] {searchValue4,start4,end4},new RowMapper<NBoardDTO4>() {
				
				public NBoardDTO4 mapRow(ResultSet rs, int rowNum) throws SQLException{
					NBoardDTO4 dto4 = new NBoardDTO4();
					dto4.setNum4(rs.getInt("num4"));
					dto4.setName4(rs.getString("name4"));
					dto4.setSubject4(rs.getString("subject4"));
					dto4.setCreated4(rs.getString("created4"));
					dto4.setHitCount4(rs.getInt("hitCount4"));
					return dto4;
				}			
			});
		return lists4;
	}
	
	public int getDataCount4(String searchKey4,String searchValue4){
		
		int result4 = 0;
		StringBuilder sbuilder = new StringBuilder();
		searchValue4 = "%" + searchValue4 + "%";
			
		sbuilder.append("select nvl(count(*),0) from nboard4 ");
		sbuilder.append("where " + searchKey4 + " like ?");
			
		result4 = jdbcTemplate.queryForInt(sbuilder.toString(),searchValue4);
	
		return result4;	
	}
	//-----------------------------------
	
	public void updateHitCount4(int num4){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard4 set hitCount4=hitCount4 + 1 where num4=?");
		jdbcTemplate.update(sbuilder.toString(),num4);
	}
	
	
	public NBoardDTO4 getReadData4(int num4){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select num4,name4,subject4,created4,");
		sbuilder.append("hitCount4,content4 from nboard4 where num4=?");
		
		  NBoardDTO4 dtoFour = jdbcTemplate.queryForObject(sbuilder.toString(),new RowMapper<NBoardDTO4>() {
		  
		  @Override public NBoardDTO4 mapRow(ResultSet rs, int rowNum) throws SQLException{
		  NBoardDTO4 dto4 = new NBoardDTO4();
		  dto4.setNum4(rs.getInt("num4")); 
		  dto4.setName4(rs.getString("name4"));
		  dto4.setSubject4(rs.getString("subject4"));
		  dto4.setCreated4(rs.getString("created4")); 
		  dto4.setHitCount4(rs.getInt("hitCount4"));
		  dto4.setContent4(rs.getString("content4"));
		  return dto4; } 
		  },num4);
		return dtoFour;
	}
	
	public void deleteData4(int num4){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("delete nboard4 where num4=?");
		jdbcTemplate.update(sbuilder.toString(),num4);	
	}
	
	public void updateData4(NBoardDTO4 dto4){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard4 set name4=?, subject4=?,");
		sbuilder.append("content4=? where num4=?");
		jdbcTemplate.update(sbuilder.toString(),dto4.getName4(),dto4.getSubject4(),dto4.getContent4(),dto4.getNum4());
	}
	

}


































