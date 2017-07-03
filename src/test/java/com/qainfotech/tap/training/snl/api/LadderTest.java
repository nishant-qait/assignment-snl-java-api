package com.qainfotech.tap.training.snl.api;

import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class LadderTest {
  @Test
  public void check_ladder_move() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
	  
	  Board board = new Board();
	  JSONObject dataObject = board.getData();
	  
	  JSONArray step = (JSONArray)dataObject.get("steps");
	  JSONObject temp;
	  
	  for(int position = 1; position<7; position++)
	  {
		  temp = (JSONObject)step.get(position);
		  temp.put("number", position);
		  temp.put("type", 2);
		  temp.put("target", position*5);
	  }
	  
	  for(int position = 7; position<100 ;position++ )
	  {
		  temp = (JSONObject)step.get(position);
		  temp.put("number", position);
		  temp.put("type", 0);
		  temp.put("target", position);
	  }
	  
	  board.registerPlayer("nishant1");
	  board.registerPlayer("nishant2");
	  board.registerPlayer("nishant3");
	  
	  List<UUID> uuid = new ArrayList<>();
	  JSONArray playerObject = (JSONArray)dataObject.get("players");
	  
	  for(int i=0;i<playerObject.length();i++)
	  {
		  temp =(JSONObject)playerObject.get(i);
		  uuid.add((UUID)temp.get("uuid"));
	  }
	  
	  dataObject.put("turn", 2);
	 JSONObject response = board.rollDice((UUID)uuid.get(2));
      temp = (JSONObject)playerObject.get(2);
      int currentPosition = (Integer)response.get("dice")*5;
      
	  assertThat(currentPosition).isEqualTo((Integer)temp.get("position"));
   }
}
