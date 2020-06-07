package com.snehal.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.snehal.demo.model.CoronaStats;

@Service
public class CoronaVirusService {
	
	
	private String virusDataUri="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<CoronaStats> allStats=new ArrayList<CoronaStats>();
    
    
	
	public List<CoronaStats> getAllStats() {
		return allStats;
	}



	@PostConstruct
	@Scheduled(cron="0 30 10 * * ?")
	public void fetchData() throws Exception {
		List<CoronaStats> newStats=new ArrayList<CoronaStats>();
		StringBuffer response = new StringBuffer();
		URL obj = new URL(virusDataUri);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				response.append("\n");
			}
			in.close();

			// print result
			//System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		
		StringReader in = new StringReader(response.toString());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			CoronaStats c=new CoronaStats();
		    c.setState(record.get("Province/State"));
		    c.setCountry(record.get("Country/Region"));
		    int latestTotalCases=Integer.parseInt(record.get(record.size()-1));
		    int prevDayCases=Integer.parseInt(record.get(record.size()-2));
		    c.setLatestTotalCases(latestTotalCases);
		    c.setDiffFromPrevDay(latestTotalCases-prevDayCases);
		    newStats.add(c);
		    
		}
		this.allStats=newStats;
	}
}
