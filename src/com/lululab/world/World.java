package com.lululab.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lululab.entities.Enemy;
import com.lululab.entities.Entity;
import com.lululab.entities.Hatsu;
import com.lululab.entities.Lifepack;
import com.lululab.entities.Nen;
import com.lululab.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

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
						BufferedImage[] buf = new BufferedImage[3];
							buf[0] = Game.spritesheet.getSprite(0 , 32, 16, 16);
							buf[1] = Game.spritesheet.getSprite(16 , 32, 16, 16);
							buf[2] = Game.spritesheet.getSprite(32 , 32, 16, 16);
						Enemy en = new Enemy(xx*16, yy*16, 16, 16, buf);
						Game.entities.add(en);
						Game.enemies.add(en);
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
						
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFree(int xNext, int yNext) {

		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;

		int x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;

		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4) + 1;
		int yfinal = ystart + (Game.HEIGHT >> 4);
		// >> 4 = x / 16.
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}

	}
}
