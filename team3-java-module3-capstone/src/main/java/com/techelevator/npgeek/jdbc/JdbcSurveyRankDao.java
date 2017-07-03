package com.techelevator.npgeek.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.SurveyRankDao;
import com.techelevator.npgeek.model.SurveyRank;

	@Component
	public class JdbcSurveyRankDao implements SurveyRankDao {

	private JdbcTemplate jdbcTemplate;
		
		@Autowired
		public JdbcSurveyRankDao(DataSource datasource) {
			this.jdbcTemplate = new JdbcTemplate(datasource);
		}

		public List<SurveyRank> getFavorites() {
			List<SurveyRank> favorites = new ArrayList<SurveyRank>();
			String sqlTopParks = "SELECT parkcode, COUNT(parkcode) AS ranking FROM survey_result GROUP BY parkcode ORDER BY ranking DESC;";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlTopParks);
			
			while(results.next()) {
				favorites.add(mapRowToSurveyRank(results));
			}
			return favorites;
		}
		
		private SurveyRank mapRowToSurveyRank(SqlRowSet results) {
			SurveyRank surveyRank = new SurveyRank();
			surveyRank.setParkCode(results.getString("parkcode"));
			surveyRank.setRanking(results.getInt("ranking"));
			
			return surveyRank;
		}
	}

