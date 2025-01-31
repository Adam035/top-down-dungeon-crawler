package com.lipian.dungeoncrawler.world;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LocationLoader {
    public static Location getMap(String filePath) {
        Location map = new Location();
        try (Stream<String> stream =  Files.lines(Paths.get(filePath))) {
            stream.flatMap(line -> Stream.of(line.split(",")))
                    .map(String::trim)
                    .map(LocationLoader::parseTile)
                    .forEach(map::addTile);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return map;
    }

    private static Tile parseTile(String s) {
        Tile tile;
        switch (s) {
            case "0" -> tile = new Floor();
            case "1" -> tile = new Wall("wall-up");
            case "2" -> tile = new Wall("wall-right");
            case "3" -> tile = new Wall("wall-down");
            case "4" -> tile = new Wall("wall-left");
            case "5" -> tile = new Wall("wall-up-right-concave");
            case "6" -> tile = new Wall("wall-down-right-concave");
            case "7" -> tile = new Wall("wall-down-left-concave");
            case "8" -> tile = new Wall("wall-up-left-concave");
            case "9" -> tile = new Wall("wall-up-right");
            case "10" -> tile = new Wall("wall-down-right");
            case "11" -> tile = new Wall("wall-down-left");
            case "12" -> tile = new Wall("wall-up-left");
            default -> tile = null;
        }
        return tile;
    }
}

