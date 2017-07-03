package com.techelevator.npgeek.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.dao.ParkDao;
import com.techelevator.npgeek.dao.SurveyDao;
import com.techelevator.npgeek.dao.SurveyRankDao;
import com.techelevator.npgeek.dao.WeatherDao;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyRank;
import com.techelevator.npgeek.model.Weather;

@Controller
public class NPGeekController {

	@Autowired
	ParkDao parkDao;
	
	@Autowired
	WeatherDao weatherDao;
	
	@Autowired
	SurveyDao surveyDao;
	
	@Autowired
	SurveyRankDao surveyRankDao;
	
	@RequestMapping(path={"/", "homePage"}, method=RequestMethod.GET)
	public String showHomePage(Model modelHolder) {
		List<Park> parks = parkDao.getAllParks();
		modelHolder.addAttribute("parks", parks);
		
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetails", method=RequestMethod.GET)
	public String showDetailsPage(Model modelHolder, @RequestParam String parkCode) {
		Park parkToView = parkDao.getPark(parkCode);
		List<Weather> weatherForPark = weatherDao.getParkWeather(parkToView);
		Weather todaysWeather = weatherForPark.get(0);
		
		modelHolder.addAttribute("park", parkToView);
		modelHolder.addAttribute("weather", weatherForPark);
		modelHolder.addAttribute("todaysWeather", todaysWeather);
		
		return "parkDetails";
	}
	
	@RequestMapping(path="/parkDetails", method=RequestMethod.POST)
	public String changeTemperatureValue(HttpSession session, @RequestParam String tempType, @RequestParam String parkCode) {
		if(tempType.equals("C")) {
			Boolean celcius = true;
			session.setAttribute("celcius", celcius);
		} else {
			Boolean celcius = false;
			session.setAttribute("celcius", celcius);
		}
		
		
		return "redirect:/parkDetails?parkCode=" + parkCode;
	}
	
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String showSurveyForm(Model modelHolder) {
		List<Park> parks = parkDao.getAllParks();
		if(!modelHolder.containsAttribute("survey")) {
			Survey survey = new Survey();
			modelHolder.addAttribute("survey", survey);
		}
		modelHolder.addAttribute("parks", parks);
		
		return "surveyInput";
	}
	
	@RequestMapping(path="/surveySubmit", method=RequestMethod.POST)
	public String makeSurveyEntry(@Valid @ModelAttribute Survey survey, BindingResult result, RedirectAttributes flash) {
		flash.addFlashAttribute("survey", survey);
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		surveyDao.saveSurvey(survey);
		return "redirect:/surveyResults";
	}
	
	@RequestMapping(path="/surveyResults", method=RequestMethod.GET)
	public String showSurveyResults(Model modelHolder) {
		Map<Park, Integer> rankingParksMap = new LinkedHashMap<>();
		List<SurveyRank> rankings = surveyRankDao.getFavorites();
		List<String> parkCodes = new ArrayList<>();
		for(SurveyRank element : rankings) {
			parkCodes.add(element.getParkCode());
		}
		List<Park> parks = new ArrayList<>();
		for(String element : parkCodes) {
			parks.add(parkDao.getPark(element));
		}
		for(int i = 0; i < parks.size(); i++) {
			rankingParksMap.put(parks.get(i), rankings.get(i).getRanking());
		}
		modelHolder.addAttribute("rankMap", rankingParksMap);
		
		return "surveyResults";
	}
}
