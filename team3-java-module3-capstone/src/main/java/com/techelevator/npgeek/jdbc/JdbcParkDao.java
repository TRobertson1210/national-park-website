package com.techelevator.npgeek.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.model.Park;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM park");
		while(results.next()) {
			parks.add(mapRowToPark(results));
		}
		return parks;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park thisPark = new Park();
		thisPark.setAcreage(results.getInt("acreage"));
		thisPark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		thisPark.setClimate(results.getString("climate"));
		thisPark.setDescription(results.getString("parkdescription"));
		thisPark.setElevationInFeet(results.getInt("elevationinfeet"));
		thisPark.setEntryFee(results.getBigDecimal("entryfee"));
		thisPark.setInspirationalQuote(results.getString("inspirationalquote"));
		thisPark.setMilesOfTrail(results.getDouble("milesoftrail"));
		thisPark.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		thisPark.setNumberOfCampsites(results.getInt("numberofcampsites"));
		thisPark.setParkCode(results.getString("parkcode"));
		thisPark.setParkName(results.getString("parkname"));
		thisPark.setState(results.getString("state"));
		thisPark.setYearFounded(results.getInt("yearfounded"));
		thisPark.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		
		return thisPark;
	}

	@Override
	public Park getPark(String parkCode) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM park WHERE parkcode = ?", parkCode);
		
		if(results.next()) {
			return mapRowToPark(results);
		} else {
			return null;
		}
	}
}
