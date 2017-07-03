package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class BasicRuleTest {
	
  @Test(expectedExceptions=MaxPlayersReachedExeption.class)
  public void should_throw_MaxPlayersReachedExeption() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {

	  Board board =new Board();
	  board.registerPlayer("agarwal1");
	  board.registerPlayer("agarwal2");
	  board.registerPlayer("agarwal3");
	  board.registerPlayer("agarwal4");
	  board.registerPlayer("agarwal5");
	  
  
  }
  @Test (expectedExceptions=PlayerExistsException.class)
  public void should_throw_PlayerExistsException() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption
  {
  Board board =new Board();
  board.registerPlayer("agarwal1");
  board.registerPlayer("agarwal2");
  board.registerPlayer("agarwal1");
    
  }

  @Test (expectedExceptions = GameInProgressException.class)
  public void should_throw_GameInProgressException() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException
  {
	  Board board =new Board();
	  board.registerPlayer("agarwal1");
	  board.registerPlayer("agarwal2");
	  board.registerPlayer("agarwal3");
	  
	  List<UUID> uuid =new ArrayList<>();
	  
	  JSONObject dataObject = board.getData();
	  JSONArray  playerArray= (JSONArray)dataObject.get("players");
	  
	  for(int i=0; i<playerArray.length();i++)
	  {
		 JSONObject player = (JSONObject)playerArray.get(i);
		 uuid.add((UUID)player.get("uuid"));
	  }
	  board.rollDice(uuid.get(0));
	  board.registerPlayer("agarwal4");
	  
  }
 
  @Test 
  public void should_throw_NoUserWithSuchUUIDException() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException
  {
	  Board board =new Board();
	  board.registerPlayer("agarwal1");
	  board.registerPlayer("agarwal2");
	  board.registerPlayer("agarwal3");
	  
	  List<UUID> uuid =new ArrayList<>();
	  
	  JSONObject dataObject = board.getData();
	  JSONArray  playerArray= (JSONArray)dataObject.get("players");
	  
	  for(int i=0; i<playerArray.length();i++)
	  {
		 JSONObject player = (JSONObject)playerArray.get(i);
		 uuid.add((UUID)player.get("uuid"));
	  }
	  
	  System.out.println(playerArray.length()+"................before deletion");
	  
	  
	  UUID deleteUuid = uuid.get(1);
	  board.deletePlayer(deleteUuid);
	  uuid.remove(deleteUuid);

	  JSONObject temp = board.getData();
	  int turn = (Integer)temp.get("turn");
	  
	  temp.put("turn", 0);
	
	 System.out.println("deleting ..............."+deleteUuid);
	  System.out.println(playerArray.length()+"................after deletion");
	  for(int i=0; i<playerArray.length();i++)
	  {
		 JSONObject player = (JSONObject)playerArray.get(i);
		 System.out.println((UUID)player.get("uuid"));
	  }
	  System.out.println(uuid.get(1)+"accsessssss");
	  board.rollDice(uuid.get(0));
	  System.out.println("always runs...................oooo");
  }
  
  
  @Test (expectedExceptions = InvalidTurnException.class)
  public void should_throw_InvalidTurnException() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException
  {
	  Board board =new Board();
	  board.registerPlayer("agarwal1");
	  board.registerPlayer("agarwal2");
	  board.registerPlayer("agarwal3");
	  
	  List<UUID> uuid =new ArrayList<>();
	  
	  JSONObject dataObject = board.getData();
	  JSONArray  playerArray= (JSONArray)dataObject.get("players");
	  
	  for(int i=0; i<playerArray.length();i++)
	  {
		 JSONObject player = (JSONObject)playerArray.get(i);
		 uuid.add((UUID)player.get("uuid"));
	  }
	  board.rollDice(uuid.get(1));
	  
  }
  
}
