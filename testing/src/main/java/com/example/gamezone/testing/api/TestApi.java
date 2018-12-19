package com.example.gamezone.testing.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamezone.testing.bean.Chat;
import com.example.gamezone.testing.bean.ExtraDetails;
import com.example.gamezone.testing.bean.FavouriteTeam;
import com.example.gamezone.testing.bean.Ids;
import com.example.gamezone.testing.bean.Notification;
import com.example.gamezone.testing.bean.SignIn;
import com.example.gamezone.testing.services.GameService;


@CrossOrigin(origins = "https://ec2-54-145-106-140.compute-1.amazonaws.com:8585")
@RestController
public class TestApi {
	
	@Autowired
	GameService game;
	
	@GetMapping("/getDetails/{id}")
    public List<SignIn> index(@PathVariable String id) {
		System.out.println("in get Details"+id);
		List<SignIn> list = new ArrayList<>();
        list.add(game.getSignInById(id));
        return list;
    }
	@GetMapping("/getFavouriteTeams/{id}")
    public List<FavouriteTeam> getFavouriteTeams(@PathVariable String id) {
		System.out.println("in getFavouriteTeams"+id);
		return game.getFavouriteTeamById(id);
    }
	@GetMapping("/getAll")
    public List<SignIn> getAll() {
		System.out.println("in get all");
		List<SignIn> list = game.getAll();
        return list;
    }
	@PostMapping("/testScoreoard")
    public String testScoreoard(@RequestBody String string) {
		System.err.println(string);
        return game.test(string);
    }
	@PostMapping("/ranking")
    public String ranking(@RequestBody String string) {
		System.err.println(string);
        return game.ranking(string);
    }
	@PostMapping("/externalTeams")
    public String externalTeams(@RequestBody String string) {
		System.err.println(string);
        return game.externalTeams(string);
    }
	
	
	@PostMapping(path = "/insertFavTeam")
    public List<String> insertFavTeam(@RequestBody FavouriteTeam favouriteTeam) {
		List<String> list = new ArrayList<>();
		list.add(game.insertTeamAsFav(favouriteTeam));
        return list;
    }

	@PostMapping(path = "/signIn")
	public List<String> signIn(@RequestBody SignIn signIn) {
		System.out.println("in signin" +signIn.toString());
		game.signIn(signIn);
		List<String> list = new ArrayList<>();
		list.add("Success");
		return list;
	}
	
	@PostMapping(path = "/block")
	public List<String> block(@RequestBody SignIn signIn) {
		System.out.println("in block"+signIn.toString());
		
		List<String> list = new ArrayList<>();
		list.add(game.block(signIn));
		return list;
	}
	
	@PostMapping(path = "/extraDetails")
	public List<String> details(@RequestBody ExtraDetails ed) {
		System.out.println("in extra details " +ed.toString());
		List<String> list = new ArrayList<>();
		list.add(game.extraDetails(ed));
		return list;
	}
	
	@PostMapping("/removeFav")
	public List<String> removeFav(@RequestBody Ids ids) {
		List<String> list = new ArrayList<>();
		list.add(game.removeFromFavourite(ids.getTeamId(),ids.getUserId()));
		return list;
	}
	
	@GetMapping("/getAllNotification/{string}")
	public List<Notification> getAllNotification(@PathVariable String string){
		return game.getAllNotification(string);
	}
	
	@GetMapping("/removeNotificationById/{string}")
	public List<String> removeNotificationById(@PathVariable String string){
		List<String> list = new ArrayList<>();
		list.add(game.removeNotificationById(string));
		return list;
	}
	
	@PostMapping("/addNotification")
	public List<String> addNotification(@RequestBody List<Notification> notifications){
//		System.err.println(notifications.get(0).getMessage());
//		System.err.println(notifications.get(0).getNotifyDate());
//		System.err.println(notifications.get(0).getUserId());
//		System.err.println(notifications.get(0).isSeen());
//		System.err.println(notifications.get(0).getGameId());
		List<String> list = new ArrayList<>();
		list.add(game.addNotification(notifications));
		return list;
	}
	
	@PostMapping("/addChatMessage")
	public List<String> addChat(@RequestBody Chat chat){
		List<String> list = new ArrayList<>();
		System.err.println(chat.getMessage());
//		Chat chat = new Chat();
//		chat.setMessage("Hi");
//		chat.setSender("Debjyoti Pandit");
//		chat.setTeamId((long) 1610612765);
//		chat.setDateOfSending("02-12-2018 17:14");
//		chat.setsID("1391208581015505");
		list.add(game.addChat(chat));
		return list;
	}
	
	@GetMapping("/getAllChats/{string}")
	public List<Chat> getAllChats(@PathVariable String string){
		return game.getAllChats(string);
	}
}
