package challengeMailMergerCsvTotTxt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

public class CsvTxtMerger {

	private static final int MINIMUM_LOGGEDON_DURATION_REQUIRED = 100;

	public static void main(String[] args) {

		File file = new File("input-1.csv");
		String line;

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// Read header line
			line = br.readLine();

			// First line
			line = br.readLine();

			while (line != null) {

				String[] splitDetails = line.split(",");

				String name = splitDetails[0];
				String studentNumber = splitDetails[1];
				String logon = splitDetails[2];
				String logoff = splitDetails[3];
				String email = splitDetails[4];

				LocalTime logonTime = LocalTime.parse(logon);
				LocalTime logoffTime = LocalTime.parse(logoff);

				Duration duration = Duration.between(logonTime, logoffTime);

				long diffInMinutes = duration.toMinutes();

				if (diffInMinutes < MINIMUM_LOGGEDON_DURATION_REQUIRED) {

					File file2 = new File(name + ".txt");

					FileWriter fw = new FileWriter(file2);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write("Dear " + name + ",\n\n");
					bw.write("You didn't last too long at the lecture today! (" + diffInMinutes
							+ " mins.) You need to stay for the full duration.\n\n");
					bw.write("Name: " + name + "\n");
					bw.write("Student number: " +studentNumber + "\n");
					bw.write("Logon time: " +logon + "\n");
					bw.write("Logff time: " +logoff + "\n");
					bw.write("Email: " +email + "\n");
					
					bw.close();
				} else {
					
					File file2 = new File(name + ".txt");

					FileWriter fw = new FileWriter(file2);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write("Dear " + name + ",\n\n");
					bw.write("You did great at the lecture today! (" + diffInMinutes
							+ " mins.).\n\n");
					bw.write("Name: " + name + "\n");
					bw.write("Student number: " +studentNumber + "\n");
					bw.write("Logon time: " +logon + "\n");
					bw.write("Logff time: " +logoff + "\n");
					bw.write("Email: " +email + "\n");
					
					bw.close();
					
				}

				line = br.readLine();

			}
			
			br.close();
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
