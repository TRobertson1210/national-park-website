package com.techelevator.npgeek.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.WeatherDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Weather> getParkWeather(Park parkToView) {
		
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM weather WHERE parkCode = ?", parkToView.getParkCode()); 
		List<Weather> weatherForecast = new ArrayList<>();
		while(results.next()) {
			weatherForecast.add(mapRowToWeather(results));
		} 
		return weatherForecast;
		
	}

	
	private Weather mapRowToWeather(SqlRowSet results) {
		Weather thisWeather = new Weather();
		thisWeather.setParkCode(results.getString("parkCode"));
		thisWeather.setFiveDayForecastValue(results.getInt("fiveDayForecastValue"));
		thisWeather.setLow(results.getInt("low"));
		thisWeather.setHigh(results.getInt("high"));
		thisWeather.setForecast(results.getString("forecast"));
		return thisWeather;
	}

}
