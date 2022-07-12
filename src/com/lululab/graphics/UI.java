package com.lululab.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.lululab.main.Game;

public class UI {
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(5, 10, 50, 10);
		g.setColor(Color.green);
		g.fillRect(5, 10, (int)((Game.player.life/Game.player.maxlife)*50), 10);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD ,10));
		g.drawString((int)Game.player.life+"/"+(int)Game.player.maxlife, 5, 19);
	}

}
