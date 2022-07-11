package com.lululab.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lululab.entities.Entity;
import com.lululab.entities.Hatsu;
import com.lululab.entities.Lifepack;
import com.lululab.entities.Nen;
import com.lululab.entities.Pig;
import com.lululab.main.Game;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					// Padrão = Chão
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					// Chão
					if (pixelAtual == 0xFF000000) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
<<<<<<< HEAD
						// Parede
					} else if (pixelAtual == 0xFFFFFFFF) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
						// Gon
					} else if (pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx * 16);
						Game.player.setY(yy * 16);
					}
					// Pig
					else if (pixelAtual == 0xFFFF0000) {
						Game.entities.add(new Pig(xx * 16, yy * 16, 16, 16, Entity.PIG_EN));
					}
					// Hatsu
					else if (pixelAtual == 0xFFFF6A00) {
						Game.entities.add(new Hatsu(xx * 16, yy * 16, 16, 16, Entity.HATSU_EN));
					}
					// Nen
					else if (pixelAtual == 0xFF00FFFF) {
						Game.entities.add(new Nen(xx * 16, yy * 16, 16, 16, Entity.NEN_EN));
					}
					// Vida
					else if (pixelAtual == 0xFFFF7F7F) {
						Game.entities.add(new Lifepack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK_EN));
=======
					// Parede
					} else if (pixelAtual == 0xFFFFFFFF) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					// Gon
					} else if (pixelAtual == 0xFF0026FF) {
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}
					// Pig
					else if (pixelAtual == 0xFFFF0000) {
						Game.entities.add(new Pig(xx*16, yy*16, 16, 16, Entity.PIG_EN));
					}
					// Hatsu
					else if (pixelAtual == 0xFFFF6A00) {
						Game.entities.add(new Hatsu(xx*16, yy*16, 16, 16, Entity.HATSU_EN));
					}
					// Nen
					else if (pixelAtual == 0xFF00FFFF) {
						Game.entities.add(new Nen(xx*16, yy*16, 16, 16, Entity.NEN_EN));
					}
					// Vida
					else if (pixelAtual == 0xFFFF7F7F) {
						Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN));
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
<<<<<<< HEAD
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4) + 1;
		int yfinal = ystart + (Game.HEIGHT >> 4);
		// >> 4 = x / 16.
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
=======
		for (int xx = 0; xx < WIDTH; xx++) {
			for (int yy = 0; yy < HEIGHT; yy++) {
>>>>>>> 9f84f4fd624d7289facb19b8712e1cd912314669
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}

	}
}
