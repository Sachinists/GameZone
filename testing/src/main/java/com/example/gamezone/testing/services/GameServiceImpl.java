package com.example.gamezone.testing.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gamezone.testing.bean.Chat;
import com.example.gamezone.testing.bean.ExtraDetails;
import com.example.gamezone.testing.bean.FavouriteTeam;
import com.example.gamezone.testing.bean.Notification;
import com.example.gamezone.testing.bean.SignIn;
import com.example.gamezone.testing.dao.GameDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("employeeService")
@Transactional
public class GameServiceImpl implements GameService{
    @Autowired
    private GameDao dao;

	@Override
	public String signIn(SignIn signIn) {
		if((!signIn.getEmail().trim().equals("")) && (!signIn.getName().trim().equals(""))) {
			if(getSignInById(signIn.getId()) != null){
				System.out.println("Already");
				return "Already Existed";
			}else {
				dao.signIn(signIn);
			}
			return "Success";
		}
		return "Failed as you have tried to store nothing";

		
	}

	@Override
	public SignIn getSignInById(String Id) {
		return dao.getSignInById(Id);
	}

	@Override
	@Transactional
	public String extraDetails(ExtraDetails eDetails) {
		System.out.println("city"+eDetails.getCity());
		if(!eDetails.getCity().trim().equals("") && !eDetails.getCountry().trim().equals("") && !eDetails.getState().trim().equals("") && !eDetails.getPhone().trim().equals("")) {
			System.out.println("in if");
			return String.valueOf(dao.extraDetails(eDetails))+ "rows updated";
		}
		System.out.println("Sorry please enter some value and then update!!");
		return "Sorry please enter some value and then update!!";
	}

	@Override
	public List<SignIn> getAll() {
		return dao.getAll();
	}

	@Override
	@Transactional
	public String block(SignIn signIn) {
		return dao.block(signIn);
		
	}

	@Override
	@Transactional
	public String insertTeamAsFav(FavouriteTeam favouriteTeam) {
//		FavouriteTeam favouriteTeam = new FavouriteTeam();
//		favouriteTeam.setUserId("1391208581015505");
//		favouriteTeam.setAltCityName("Kolkata");
//		favouriteTeam.setFullName("Kolkata Knight Riders");
//		favouriteTeam.setCity("Kolkata");
//		favouriteTeam.setTeamId(1996);
//		favouriteTeam.setTricode("kkr");
		return dao.insertTeamAsFav(favouriteTeam);
		
	}

	@Override
	public List<FavouriteTeam> getFavouriteTeamById(String id) {
		return dao.getFavouriteTeamById(id);
	}

	@Override
	public String removeFromFavourite(String id,String userId) {
		
		return dao.removeFromFavourite(id,userId);
	}

	@Override
	public String test(String string) {
		System.out.println("EmployeeServiceImpl.test()");
		URL obj;
		HttpURLConnection con;
		StringBuffer response = null;
		String url = "http://data.nba.net/10s/prod/v1/"+string+"/scoreboard.json";
		System.out.println("URL: "+"http://data.nba.net/10s/prod/v1/"+string+"/scoreboard.json");
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return response.toString();
	}

	@Override
	public String ranking(String string) {
		URL obj;
		HttpURLConnection con;
		StringBuffer response = null;
		String url = "http://data.nba.net/10s/prod/v1/"+string+"/team_stats_rankings.json";
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return response.toString();
	}

	@Override
	public String externalTeams(String string) {
		URL obj;
		HttpURLConnection con;
		StringBuffer response = null;
		String url = "http://data.nba.net/10s/prod/v2/"+string+"/teams.json";
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return response.toString();
	}

	@Override
	public List<Notification> getAllNotification(String string) {
		return dao.getAllNotification(string);
	}

	@Override
	public String addNotification(List<Notification> notification) {
		if(notification.size() > 0)
			return dao.addNotification(notification);
		return "no notification to be added";
	}

	@Override
	public String removeNotificationById(String string) {
		// TODO Auto-generated method stub
		return dao.removeNotificationById(string);
	}

	@Override
	public String addChat(Chat chat) {
		chat.setSender(dao.getSignInById(chat.getsID()).getName());
		return dao.addChat(chat);
	}

	@Override
	public List<Chat> getAllChats(String string) {
		return dao.getAllChats(string);
	}
 
}
