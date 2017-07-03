package com.qainfotech.tap.training.snl.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class SnakeTest {
  
  @Test
  public void check_sanke_move() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
	  Board board = new Board();
	  JSONObject dataObject = board.getData();
	  
	  JSONArray step = (JSONArray)dataObject.get("steps");
	  JSONObject temp;
	  
	  for(int position = 99; position>93; position--)
	  {
		  temp = (JSONObject)step.get(position);
		  temp.put("number", position);
		  temp.put("type", 1);
		  temp.put("target",(100 - position)*10);
	  }
	  
	  for(int position = 93; position>=0 ;position-- )
	  {
		  temp = (JSONObject)step.get(position);
		  temp.put("number", position);
		  temp.put("type", 0);
		  temp.put("target", position);
	  }
	  
	  board.registerPlayer("nishantagarwal1");
	  board.registerPlayer("nishantagarwal2");
	  board.registerPlayer("nishantagarwal3");
	  
	  List<UUID> uuid = new ArrayList<>();
	  JSONArray playerObject = (JSONArray)dataObject.get("players");
	  
	  
	  
	  for(int i=0;i<playerObject.length();i++)
	  {
		  temp =(JSONObject)playerObject.get(i);
		  uuid.add((UUID)temp.get("uuid"));
		  temp.put("position", 93);
	  
	  }
	  
	  dataObject.put("turn", 2);
	 JSONObject response = board.rollDice((UUID)uuid.get(2));
      temp = (JSONObject)playerObject.get(2);
      int currentPosition = 7-(Integer)response.get("dice");
      currentPosition = currentPosition*10;
	  assertThat(currentPosition).isEqualTo((Integer)temp.get("position"));
   
  
  }
}
