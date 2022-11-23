package data_timer_DB;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class read_csv {
	public static void main(String[] args) {
		read_csv csvReader413 = new read_csv();

//		String DATA_URL = "/Users/choidowon/Desktop/smart_building/room/";
//		File dir = new File(DATA_URL);
//
//		String[] roomNames = dir.list();
//		for (String roomName : roomNames) {
//			
//		}
		
		List<String> sensorList = new ArrayList<String>();
		
		sensorList = csvReader413.readCSV(0,"413");
		
		System.out.println(sensorList);

	}

	public List<String> readCSV(int pageNum,String room) {
		String line = "";
		List<String> sensorList = new ArrayList<String>();
		Path co2path = Paths.get("/Users/choidowon/Desktop/smart_building/room/" + room + "/co2.csv");
		Path humpath = Paths.get("/Users/choidowon/Desktop/smart_building/room/" + room + "/humidity.csv");
		Path litpath = Paths.get("/Users/choidowon/Desktop/smart_building/room/" + room + "/light.csv");
		Path pirpath = Paths.get("/Users/choidowon/Desktop/smart_building/room/" + room + "/pir.csv");
		Path tempath = Paths.get("/Users/choidowon/Desktop/smart_building/room/" + room + "/temperature.csv");
		
		try {
			//co2 n번째 라인 읽어오기
			Stream<String> stream = Files.lines(co2path);
			line = stream.skip(pageNum).findFirst().get();
			String[] lineArr = line.split(",");
			line = lineArr[1];
			//sensorList에 co2 n번째 값 추가
			sensorList.add(line);
			//hum n번째 라인 읽어오기
			stream = Files.lines(humpath);
			line = stream.skip(pageNum).findFirst().get();
			lineArr = line.split(",");
			line = lineArr[1];
			//sensorList에 hum n번째 값 추가
			sensorList.add(line);
			//hum n번째 라인 읽어오기
			stream = Files.lines(litpath);
			line = stream.skip(pageNum).findFirst().get();
			lineArr = line.split(",");
			line = lineArr[1];
			//sensorList에 hum n번째 값 추가
			sensorList.add(line);
			//hum n번째 라인 읽어오기
			stream = Files.lines(pirpath);
			line = stream.skip(pageNum).findFirst().get();
			lineArr = line.split(",");
			line = lineArr[1];
			//sensorList에 hum n번째 값 추가
			sensorList.add(line);
			//hum n번째 라인 읽어오기
			stream = Files.lines(tempath);
			line = stream.skip(pageNum).findFirst().get();
			lineArr = line.split(",");
			line = lineArr[1];
			//sensorList에 hum n번째 값 추가
			sensorList.add(line);
			
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sensorList;
	}
}
