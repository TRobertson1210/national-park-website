package com.techelevator.npgeek.dao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyRank;

public interface SurveyRankDao {

	List<SurveyRank> getFavorites();
}
