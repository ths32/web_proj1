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


import com.exe.dto.NBoardDTO3;

public class NBoardDAO3 {
	
	private JdbcTemplate jdbcTemplate; //modified
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws Exception{ //modified
		this.jdbcTemplate = jdbcTemplate; //modified
	}
	
	public int getMaxNum3(){
		
		int maxNum3 = 0;
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select nvl(max(num3),0) from nboard3"); //!!!
		maxNum3 = jdbcTemplate.queryForInt(sbuilder.toString());
		return maxNum3;
		
	}
	
	public void insertData3(NBoardDTO3 dto3){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("insert into nboard3 (num3,name3,subject3,created3,hitCount3,content3) values(?,?,?,sysdate,0,?)");
		jdbcTemplate.update(sbuilder.toString(),dto3.getNum3(),dto3.getName3(),dto3.getSubject3(),dto3.getContent3());
	}
	
	
	public List<NBoardDTO3> getList3(int start3, int end3, String searchKey3, String searchValue3){
		
			StringBuilder sbuilder = new StringBuilder();
			searchValue3 = "%" + searchValue3 + "%";
			sbuilder.append("select * from (");
			sbuilder.append("select rownum rnum,data.* from(");
			sbuilder.append("select num3,name3,subject3,to_char(created3,'YYYY-MM-DD') created3,hitCount3 ");
			sbuilder.append("from nboard3 where " + searchKey3 + " like ? order by num3 desc) data) ");
			sbuilder.append("where rnum >= ? and rnum <= ?");
			
			List<NBoardDTO3> lists3 = jdbcTemplate.query(sbuilder.toString(),new Object[] {searchValue3,start3,end3},new RowMapper<NBoardDTO3>() {
				
				public NBoardDTO3 mapRow(ResultSet rs, int rowNum) throws SQLException{
					NBoardDTO3 dto3 = new NBoardDTO3();
					dto3.setNum3(rs.getInt("num3"));
					dto3.setName3(rs.getString("name3"));
					dto3.setSubject3(rs.getString("subject3"));
					dto3.setCreated3(rs.getString("created3"));
					dto3.setHitCount3(rs.getInt("hitCount3"));
					return dto3;
				}			
			});
		return lists3;
	}
	
	public int getDataCount3(String searchKey3,String searchValue3){
		
		int result3 = 0;
		StringBuilder sbuilder = new StringBuilder();
		searchValue3 = "%" + searchValue3 + "%";
			
		sbuilder.append("select nvl(count(*),0) from nboard3 ");
		sbuilder.append("where " + searchKey3 + " like ?");
			
		result3 = jdbcTemplate.queryForInt(sbuilder.toString(),searchValue3);
	
		return result3;	
	}
	//-----------------------------------
	
	public void updateHitCount3(int num3){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard3 set hitCount3=hitCount3 + 1 where num3=?");
		jdbcTemplate.update(sbuilder.toString(),num3);
	}
	
	
	public NBoardDTO3 getReadData3(int num3){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select num3,name3,subject3,created3,");
		sbuilder.append("hitCount3,content3 from nboard3 where num3=?");
		
		  NBoardDTO3 dtoThree = jdbcTemplate.queryForObject(sbuilder.toString(),new RowMapper<NBoardDTO3>() {
		  
		  @Override public NBoardDTO3 mapRow(ResultSet rs, int rowNum) throws SQLException{
		  NBoardDTO3 dto3 = new NBoardDTO3();
		  dto3.setNum3(rs.getInt("num3")); 
		  dto3.setName3(rs.getString("name3"));
		  dto3.setSubject3(rs.getString("subject3"));
		  dto3.setCreated3(rs.getString("created3")); 
		  dto3.setHitCount3(rs.getInt("hitCount3"));
		  dto3.setContent3(rs.getString("content3"));
		  return dto3; } 
		  },num3);
		return dtoThree;
	}
	
	public void deleteData3(int num3){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("delete nboard3 where num3=?");
		jdbcTemplate.update(sbuilder.toString(),num3);	
	}
	
	public void updateData3(NBoardDTO3 dto3){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard3 set name3=?, subject3=?,");
		sbuilder.append("content3=? where num3=?");
		jdbcTemplate.update(sbuilder.toString(),dto3.getName3(),dto3.getSubject3(),dto3.getContent3(),dto3.getNum3());
	}
	

}


































