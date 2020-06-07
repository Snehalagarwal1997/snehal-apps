package com.snehal.demo.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.snehal.demo.model.CoronaStats;
import com.snehal.demo.service.CoronaVirusService;

@Controller
public class CoronaController {

	@Autowired
	CoronaVirusService service;
	
	@GetMapping("/corona")
	public String showData(Model m) {
		
		List<CoronaStats> allStats=service.getAllStats();
		ListIterator<CoronaStats> listIterator = allStats.listIterator();
		int totalCases=0;
		int totalNewCases=0;
		int india=0;
		String highestCases="";
		int h=0;
		while(listIterator.hasNext()) {
			CoronaStats c=listIterator.next();
			/*if(c.getState()=="India") {india=c.getLatestTotalCases();}*/
			int x=c.getDiffFromPrevDay();
		    totalCases=totalCases+c.getLatestTotalCases();
		    totalNewCases=totalNewCases+c.getDiffFromPrevDay();
		    if(x>h)
		    {
		    	h=c.getDiffFromPrevDay();
		    	highestCases=c.getCountry();
		    }
		}
		m.addAttribute("allStats",allStats);
		m.addAttribute("totalCases",totalCases);
		m.addAttribute("totalNewCases",totalNewCases);
		m.addAttribute("highestCases",highestCases);
		m.addAttribute("india",india);
		
		return "corona";
	}
}
