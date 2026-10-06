package src;

// import java.util.Random;

import src.classes.*;

import static inc.Utils.*;

import java.io.InputStream;

public class Main
{

	private static void EXIT1(String errorMessage) {
		System.err.println("Error: " + errorMessage);
		System.exit(1);
	}

	// Scenario file format:
	// First line contains a positive integer, represents how many
	// times the simulation runs.
	// Each subsequent line describes an aircraft that will be part of the simulation, using this
	// format: TYPE NAME LONGITUDE LATITUDE HEIGHT.
	private static void	loadScenario(String filePath)
	{
		InputStream scenerioStream = openFileRead(filePath);

		if (scenerioStream == null	)
			EXIT1("Could not open scenario file: " + filePath);

		String firstLine = getNextLine(scenerioStream);
		if (firstLine == null)
			EXIT1("Could not read the first line of the scenario file.");

		int simulationCount = 0;
		try {
			simulationCount = stringToNumeric(firstLine, Integer.class);

			if (simulationCount <= 0)
				EXIT1("The first line of the scenario file must be a positive integer.");
		} catch (Exception ex) {
			EXIT1("The first line of the scenario file must be a integer.");
		}

		while (true)
		{
			String line = getNextLine(scenerioStream);
			if (line == null)
				break ;

			try {
				AFlyable flyable = AircraftFactory.newAircraft(line);
				if (flyable != null)
					flyable.registerTower(WeatherTower.getInstance());
			} catch (Exception ex) {
				System.err.println("Error: " + ex.getMessage());
			}
		}
	}

	public static void	main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("Usage: java Main <scenario_file>");
			System.exit(1);
		}

		loadScenario(args[0]);
	}
}
