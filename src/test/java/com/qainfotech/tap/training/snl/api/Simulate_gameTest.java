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

public class Simulate_gameTest {
	@Test
	public void Show_Winner()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException, NoUserWithSuchUUIDException {

		Board board = new Board();
		board.registerPlayer("jai");
		board.registerPlayer("veeru");
		board.registerPlayer("dhano");
		board.registerPlayer("basanti");

		JSONObject dataObject = board.getData();
		JSONArray playerArray = (JSONArray) dataObject.get("players");
		JSONObject temp;
		List<UUID> uuid = new ArrayList<>();
		for (int i = 0; i < playerArray.length(); i++) {
			temp = (JSONObject) playerArray.get(i);
			uuid.add((UUID) temp.get("uuid"));
		}

		boolean stop = true;
		JSONObject message;
		int rank = 1;
		for (int i = 0; stop; i++) {
			if (i >= playerArray.length())
				i = 0;
			System.out.println();
			message = board.rollDice(uuid.get(i));
			
			System.out.println(message.get("playerName"));
			System.out.println(message.get("message"));
			System.out.println(message.get("playerUuid"));
			System.out.println(message.get("dice"));

			temp = (JSONObject) playerArray.get(i);
			int position = (Integer) temp.get("position");

			if (position == 100) {
				System.out.println(temp.get("name") + "......got......" + rank);

				board.deletePlayer(uuid.get((i)));
				uuid.remove(uuid.get(i));
				if (i >= playerArray.length())
					i = 0;
				
				
		  	if(rank!=3)
				dataObject.put("turn", (i));
				
				
				rank++;
				if (uuid.size() == 1) {
					temp = (JSONObject) playerArray.get((i));
					System.out.println(temp.get("name") + ".........got....." + rank);
					rank++;
					stop = false;
				}
              i--;
			}
		
		}

	}
}
