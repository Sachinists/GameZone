package com.example.gamezone.testing.services;

import java.util.List;

import com.example.gamezone.testing.bean.Chat;
import com.example.gamezone.testing.bean.ExtraDetails;
import com.example.gamezone.testing.bean.FavouriteTeam;
import com.example.gamezone.testing.bean.Notification;
import com.example.gamezone.testing.bean.SignIn;

public interface GameService {

    String signIn(SignIn signIn);
    SignIn getSignInById(String Id);
    String extraDetails(ExtraDetails eDetails);
	List<SignIn> getAll();
	String block(SignIn signIn);
	String insertTeamAsFav(FavouriteTeam favouriteTeam);
	List<FavouriteTeam> getFavouriteTeamById(String id);
	String removeFromFavourite(String id,String userId);
	String test(String string);
	String ranking(String string);
	String externalTeams(String string);
	List<Notification> getAllNotification(String string);
	String addNotification(List<Notification> notification);
	String removeNotificationById(String string);
	String addChat(Chat chat);
	List<Chat> getAllChats(String string);
}
