package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Weather;

public interface WeatherDao {

	List<Weather> getParkWeather(Park parkToView);
	
}
