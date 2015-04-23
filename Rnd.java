package random1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Rnd {
	
	private static int numRecords = 10000;

	public static ArrayList<Timestamp> getTime() {
		long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2012-02-01 00:00:00").getTime();
		long diff = end - offset + 1;
		int x = 0;
		ArrayList<Timestamp> list = new ArrayList<>();
		while (x < numRecords) {
			x++;
			Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));
			list.add(rand);
		}
		Collections.sort(list);
		return list;
	}

	public static String getInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return String.valueOf(randomNum);
	}
	
	private enum State {
		IN, OUT;
		
		public static State getState() {
			Random random = new Random();
            return values()[random.nextInt(values().length)];
		}
	
	};
	
	
	

	public static void main(String[] args) {
		int i =0;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("doorsLog.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (i<numRecords) {
			String t = getTime().get(i).toString();
			String d = getInt(0, 200);
			String s = State.getState().toString();
			System.out.print(t + " " + "Door_"+d + " " + s + "\n");
			writer.println(t + " " + "Door_"+d + " " + s);
			i++;

		}
		writer.close();
	}

}
