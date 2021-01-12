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


import com.exe.dto.NBoardDTO;

public class NBoardDAO {
	
	private JdbcTemplate jdbcTemplate; //modified
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) throws Exception{ //modified
		this.jdbcTemplate = jdbcTemplate; //modified
	}
	
	public int getMaxNum(){
		
		int maxNum = 0;
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select nvl(max(num),0) from nboard");
		maxNum = jdbcTemplate.queryForInt(sbuilder.toString());
		return maxNum;
		
	}
	
	public void insertData(NBoardDTO dto){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("insert into nboard (num,name,subject,created,hitCount,content) values(?,?,?,sysdate,0,?)");
		jdbcTemplate.update(sbuilder.toString(),dto.getNum(),dto.getName(),dto.getSubject(),dto.getContent());
	}
	
	
	public List<NBoardDTO> getList(int start, int end, String searchKey, String searchValue){
		
			StringBuilder sbuilder = new StringBuilder();
			searchValue = "%" + searchValue + "%";
			sbuilder.append("select * from (");
			sbuilder.append("select rownum rnum,data.* from(");
			sbuilder.append("select num,name,subject,to_char(created,'YYYY-MM-DD') created,hitCount ");
			sbuilder.append("from nboard where " + searchKey + " like ? order by num desc) data) ");
			sbuilder.append("where rnum >= ? and rnum <= ?");
			
			List<NBoardDTO> lists = jdbcTemplate.query(sbuilder.toString(),new Object[] {searchValue,start,end},new RowMapper<NBoardDTO>() {
				
				public NBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
					NBoardDTO dto = new NBoardDTO();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setSubject(rs.getString("subject"));
					dto.setCreated(rs.getString("created"));
					dto.setHitCount(rs.getInt("hitCount"));
					return dto;
				}			
			});
		return lists;
	}
	
	public int getDataCount(String searchKey,String searchValue){
		
		int result = 0;
		StringBuilder sbuilder = new StringBuilder();
		searchValue = "%" + searchValue + "%";
			
		sbuilder.append("select nvl(count(*),0) from nboard ");
		sbuilder.append("where " + searchKey + " like ?");
			
		result = jdbcTemplate.queryForInt(sbuilder.toString(),searchValue);
	
		return result;	
	}
	//-----------------------------------
	
	public void updateHitCount(int num){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard set hitCount=hitCount + 1 where num=?");
		jdbcTemplate.update(sbuilder.toString(),num);
	}
	
	
	public NBoardDTO getReadData(int num){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append("select num,name,subject,created,");
		sbuilder.append("hitCount,content from nboard where num=?");
		
		  NBoardDTO dtoOne = jdbcTemplate.queryForObject(sbuilder.toString(),new
		  RowMapper<NBoardDTO>() {
		  
		  @Override public NBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		  NBoardDTO dto = new NBoardDTO();
		  dto.setNum(rs.getInt("num")); 
		  dto.setName(rs.getString("name"));
		  dto.setSubject(rs.getString("subject"));
		  dto.setCreated(rs.getString("created")); 
		  dto.setHitCount(rs.getInt("hitCount"));
		  dto.setContent(rs.getString("content"));
		  return dto; } 
		  },num);
		return dtoOne;
	}
	
	public void deleteData(int num){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("delete nboard where num=?");
		jdbcTemplate.update(sbuilder.toString(),num);	
	}
	
	public void updateData(NBoardDTO dto){
		StringBuilder sbuilder = new StringBuilder();	
		sbuilder.append("update nboard set name=?, subject=?,");
		sbuilder.append("content=? where num=?");
		jdbcTemplate.update(sbuilder.toString(),dto.getName(),dto.getSubject(),dto.getContent(),dto.getNum());
	}
	

}


































