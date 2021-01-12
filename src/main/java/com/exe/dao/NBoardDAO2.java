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


import com.exe.dto.NBoardDTO2;

public class NBoardDAO2 {
	
	private JdbcTemplate jdbcTemplate; //modified
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws Exception{ //modified
		this.jdbcTemplate = jdbcTemplate; //modified
	}
	
	public int getMaxNum2(){
		
		int maxNum2 = 0;
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select nvl(max(num2),0) from nboard2");
		maxNum2 = jdbcTemplate.queryForInt(sbuilder.toString());
		return maxNum2;
		
	}
	
	public void insertData2(NBoardDTO2 dto2){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("insert into nboard2 (num2,subject2,created2,hitCount2,content2) values(?,?,sysdate,0,?)");
		jdbcTemplate.update(sbuilder.toString(),dto2.getNum2(),dto2.getSubject2(),dto2.getContent2());
	}
	
	
	public List<NBoardDTO2> getList2(int start2, int end2, String searchKey2, String searchValue2){
		
			StringBuilder sbuilder = new StringBuilder();
			searchValue2 = "%" + searchValue2 + "%";
			sbuilder.append("select * from (");
			sbuilder.append("select rownum rnum,data.* from(");
			sbuilder.append("select num2,subject2,to_char(created2,'YYYY-MM-DD') created2,hitCount2 ");
			sbuilder.append("from nboard2 where " + searchKey2 + " like ? order by num2 desc) data) ");
			sbuilder.append("where rnum >= ? and rnum <= ?");
			
			List<NBoardDTO2> lists2 = jdbcTemplate.query(sbuilder.toString(),new Object[] {searchValue2,start2,end2},new RowMapper<NBoardDTO2>() {
				
				public NBoardDTO2 mapRow(ResultSet rs, int rowNum) throws SQLException{
					NBoardDTO2 dto2 = new NBoardDTO2();
					dto2.setNum2(rs.getInt("num2"));
					dto2.setSubject2(rs.getString("subject2"));
					dto2.setCreated2(rs.getString("created2"));
					dto2.setHitCount2(rs.getInt("hitCount2"));
					return dto2;
				}			
			});
		return lists2;
	}
	
	public int getDataCount2(String searchKey2,String searchValue2){
		
		int result2 = 0;
		StringBuilder sbuilder = new StringBuilder();
		searchValue2 = "%" + searchValue2 + "%";
			
		sbuilder.append("select nvl(count(*),0) from nboard2 ");
		sbuilder.append("where " + searchKey2 + " like ?");
			
		result2 = jdbcTemplate.queryForInt(sbuilder.toString(),searchValue2);
	
		return result2;	
	}
	//-----------------------------------
	
	public void updateHitCount2(int num2){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard2 set hitCount2=hitCount2 + 1 where num2=?");
		jdbcTemplate.update(sbuilder.toString(),num2);
	}
	
	
	public NBoardDTO2 getReadData2(int num2){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select num2,subject2,created2,");
		sbuilder.append("hitCount2,content2 from nboard2 where num2=?");
		
		  NBoardDTO2 dtoTwo = jdbcTemplate.queryForObject(sbuilder.toString(),new 
		  RowMapper<NBoardDTO2>() {
		  
		  @Override public NBoardDTO2 mapRow(ResultSet rs, int rowNum) throws SQLException{
		  NBoardDTO2 dto2 = new NBoardDTO2();
		  dto2.setNum2(rs.getInt("num2")); 
		  dto2.setSubject2(rs.getString("subject2"));
		  dto2.setCreated2(rs.getString("created2")); 
		  dto2.setHitCount2(rs.getInt("hitCount2"));
		  dto2.setContent2(rs.getString("content2"));
		  return dto2; } 
		  },num2);
		return dtoTwo;
	}
	
	public void deleteData2(int num2){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("delete nboard2 where num2=?");
		jdbcTemplate.update(sbuilder.toString(),num2);	
	}
	
	public void updateData2(NBoardDTO2 dto2){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard2 set subject2=?,");
		sbuilder.append("content2=? where num2=?");
		jdbcTemplate.update(sbuilder.toString(),dto2.getSubject2(),dto2.getContent2(),dto2.getNum2());
	}
	

}


































