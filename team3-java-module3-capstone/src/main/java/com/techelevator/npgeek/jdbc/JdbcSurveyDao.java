package com.techelevator.npgeek.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.SurveyDao;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyRank;

@Component
public class JdbcSurveyDao implements SurveyDao {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void saveSurvey(Survey survey) {
		Long id = getNextId();
		String insertStatement = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ? ,?)";
		jdbcTemplate.update(insertStatement, id, survey.getParkCode(), survey.getEmail(), survey.getState(), survey.getActivityLevel());
	}

	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyId')";
		SqlRowSet results= jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next id");
		}
		return id;
	}
	
	
	
	private Survey mapRowToSurvey (SqlRowSet results) {
		Survey survey = new Survey();
		survey.setId(results.getInt("surveyid"));
		survey.setActivityLevel(results.getString("activitylevel"));
		survey.setEmail(results.getString("emailaddress"));
		survey.setParkCode(results.getString("parkcode"));
		survey.setState(results.getString("state"));
		survey.setCountNumber(results.getInt("ranking"));
		return survey;
	}
}