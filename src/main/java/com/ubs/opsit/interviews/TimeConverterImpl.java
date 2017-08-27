package com.ubs.opsit.interviews;

public class TimeConverterImpl implements TimeConverter {

	private String firstRow = "O";
	private String secondRow = "OOOO";
	private String thirdRow = "OOOO";
	private String fourthRow = "OOOOOOOOOOO";
	private String fifthRow = "OOOO";

	@Override
	public String convertTime(String aTime) {
		String ls = System.getProperty("line.separator");
		String[] time = aTime.split(":");
		String hours = time[0];
		String minutes = time[1];
		String seconds = time[2];

		
		
		
		
	
		return getSeconds(seconds) + ls + getHours(hours) + ls +getMinutes(minutes);
	}

	private String getMinutes(String minute) {
		String ls = System.getProperty("line.separator");
		int currentMinute = Integer.valueOf(minute);
		int numbOf1stRowLights = currentMinute / 5;
		int numbOf2ndRowLights = currentMinute - numbOf1stRowLights * 5;
		String firstLine = flashNumberOfBulbsYellow(fourthRow, numbOf1stRowLights, 4);
		String secondLine = flashNumberOfBulbsYellow(fifthRow, numbOf2ndRowLights, 5);

		return firstLine + ls + secondLine;
	}

	private String getHours(String hours) {
		String ls = System.getProperty("line.separator");
		int currentHour = Integer.valueOf(hours);
		int numbOf1stRowLights = currentHour / 5;
		int numbOf2ndRowLights = currentHour - numbOf1stRowLights * 5;

		String firstLine = flashNumberOfBulbsYellow(secondRow, numbOf1stRowLights, 2);
		String secondLine = flashNumberOfBulbsYellow(thirdRow, numbOf2ndRowLights, 3);

		return firstLine + ls + secondLine;
	}

	private String getSeconds(String seconds) {
		int currentSecond = Integer.valueOf(seconds);
		int nbr = currentSecond%2==0? 1:0;
		String second  = flashNumberOfBulbsYellow(firstRow, nbr, 1);
		
		return second;
	}
	private String flashNumberOfBulbsYellow(String bulbLine, int numbertoTurnOn, int row) {
		StringBuilder tmpBulbLine = new StringBuilder(bulbLine);

		for (int i = 0; i < numbertoTurnOn; i++) {
			tmpBulbLine.setCharAt(i, setColorBasedonRow(row, i));
		}

		return tmpBulbLine.toString();
	}

	private char setColorBasedonRow(int row, int bulbNbr) {
		char color = 'O';
		if (row != 4) {
			if (row == 2 || row == 3)
				color = 'R';
			else {
				color = 'Y';
			}

		} else { 
			
			color = (bulbNbr+1) % 3 == 0 ? 'R' : 'Y';
			//System.out.println("color: " +color);

		}
		return color;
	}

}
