package data_timer_DB;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sql {
	private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
	private static final String DB_URL = "jdbc:mariadb://localhost:3306/";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "1234";
	private static Connection con;
	private static Statement stmt;
	PreparedStatement pstmt = null;

	private static void connectDB() {
		try {
			// 데이터 객체 가져옴
			read_csv csvReader413 = new read_csv();
			// n번째 데이터 가져오
			List<String> sensorList = new ArrayList<String>();
			int count = 0;

			String DATA_URL = "/Users/choidowon/Desktop/smart_building/room/";
			File dir = new File(DATA_URL);
			String[] roomNames = dir.list();
			List<String> roomList = new ArrayList<>(Arrays.asList(roomNames));
			roomList.remove(".DS_Store");
			roomNames = roomList.toArray(new String[0]);
			
			
			while (count < 100) {
				Class.forName(DB_DRIVER_CLASS);
				Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				stmt = con.createStatement();
				//System.out.println("Statement객체 생성 성공");
				for (String roomName : roomNames) {
					// 센서 갑 불러옴
					sensorList = csvReader413.readCSV(count, roomName);

					stmt.executeUpdate("use room"); // room라는 데이터 베이스에 접속

					stmt.executeUpdate("INSERT INTO co2 (co2, createTime, room) VALUES('" + sensorList.get(0)
							+ "', NOW(), '" + roomName + "')");
					stmt.executeUpdate("INSERT INTO humidity (hum, createTime, room) VALUES('" + sensorList.get(1)
							+ "', NOW(), '" + roomName + "')");
					stmt.executeUpdate("INSERT INTO light (lit, createTime, room) VALUES('" + sensorList.get(2)
							+ "', NOW(), '" + roomName + "')");
					stmt.executeUpdate("INSERT INTO pir (pir, createTime, room) VALUES('" + sensorList.get(3)
							+ "', NOW(), '" + roomName + "')");
					stmt.executeUpdate("INSERT INTO temperature (tem, createTime, room) VALUES('" + sensorList.get(4)
							+ "', NOW(), '" + roomName + "')");
					System.out.print(roomName + " ");
				}
				stmt.close();
				con.close();
				System.out.println("");
				Thread.sleep(5000);
				
				count++;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이브 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		sql test = new sql();
		test.connectDB();
	}

}