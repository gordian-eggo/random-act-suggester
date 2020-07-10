import java.util.Random;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class RandomTODO {
	
	private Random rand = new Random(System.currentTimeMillis());
	private LinkedList<String> activities_roster = new LinkedList<String>();
	private File todo_updates;
	private String prefix = "I am depressed/lazy/dead inside but I want to do things so today I will: ";
	private int per_row;

	// getters and setters
	public LinkedList<String> get_activities_roster() { return this.activities_roster; }
	public File get_todo_updates() { return this.todo_updates; }
	public int get_per_row() { return this.per_row; }

	public void set_activities_roster(LinkedList<String> act_ros) { this.activities_roster = act_ros; }
	public void set_todo_updates(File todo_up) { this.todo_updates = todo_up; };
	public void set_per_row(int pr) { this.per_row = pr; }

	public RandomTODO() {
		System.out.println("Starting program...\n\n");
		print_menu();
		navigate_menu();
	}

	// Adds things to do to the roster
	public void add_stuff_todo() {

		Scanner input_scan = new Scanner(System.in); 

		System.out.print("\nAdd new thing to do: ");
		String input = input_scan.nextLine();

		if (!activities_roster.contains(input)) {
			activities_roster.add(input);
			System.out.println("Added the thing!\n");
		} else {
			System.out.println("You already added this!\n");
		}

	}

	// Displays entire activity roster
	public void display_activities() {

		per_row = 3;
		int counter = 0;
		
		if (!activities_roster.isEmpty()) {
			System.out.println();
			for (String i : activities_roster) {
				if (counter == 0) {
					System.out.print(i);
					counter++;
				} else if (counter > 0 && counter < 3) {
					System.out.print("\t\t" + i);
					counter++;
				} else if (counter == 3) {
					counter = 0;
					System.out.println("\n" + i);
				}
			}
			System.out.println();
		} else {
			System.out.println("\nNothing in here yet.\n\n");
		}

	}

	// Delete a thing from the list
	public void delete_thing_to_do() {
		display_activities();

		Scanner input_scan = new Scanner(System.in);
		Scanner choice_scan = new Scanner(System.in);
		boolean finished = false;

		while (!finished) {
			System.out.print("What do you want to delete? : ");
			String input = input_scan.nextLine();
			if (!activities_roster.contains(input)) {
				System.out.println("That's not on the list yo.\n");
			} else {
				System.out.println("Are you sure you want to delete '" + input + "' from the list?");
				System.out.println("[Y] Yes\t\t\t[N] No");
				System.out.print("Choice: ");
				String choice = choice_scan.nextLine();

				if (choice.equals("y") || choice.equals("Y")) {
					activities_roster.remove(input);
					System.out.println("Successfully removed '" + input + "' from the list!\n");
					finished = true;
				} else if (choice.equals("n") || choice.equals("N")) {
					System.out.println("Alright, returning...\n");
				} else {
					System.out.println("Pick a legit choice yo.\n");
				}

			}
		}
	}

	// Displays a random suggestion for what to do.
	public void random_pick() {

		Scanner rnd_picker = new Scanner(System.in);
		Scanner choice_scan = new Scanner(System.in);
		boolean satisfied = false;

		while (!satisfied) {
			display_activities();

			if (activities_roster.isEmpty()) {
				break; 
			}

			System.out.println("\nShall we pick one?");
			System.out.println("[Y] Yes\t\t\t[N] No");

			System.out.print("Choice: ");
			String choice = choice_scan.nextLine();

			if (choice.equals("y") || choice.equals("Y")) {

				System.out.println("\nPicking...\n");

				Random suggestor = new Random(System.currentTimeMillis());
				int list_index = suggestor.nextInt(activities_roster.size());

				System.out.println("Today I suggest you: " + activities_roster.get(list_index) + "\n");

			} else if (choice.equals("n") || choice.equals("N")) {
				System.out.println("Alright, returning...\n");
				break;
			} else {
				System.out.println("Pick a legit choice yo.\n");
			}

			System.out.println("Want to pick again?");
			System.out.println("[Y] Yes\t\t\t[N] No");

			System.out.print("Choice: ");
			String repick = choice_scan.nextLine();

			if (repick.equals("y") || repick.equals("Y")) {

				System.out.println("Alright hold on...\n");

			} else if (repick.equals("n") || repick.equals("N")) {
				System.out.println("Thank you for picking! Going back to main menu...\n");
				satisfied = true;
			} else {
				System.out.println("Pick a legit choice yo.\n");
			}

		}

	}

	// Prints menu
	public void print_menu() {
		System.out.println("===== RANDOM TO-DO =====");
		System.out.println("For when you feel like shit\nbut still want to be productive\n");
		System.out.println("[1] Suggest me a random thing!!");
		System.out.println("[2] Add a thing to do");
		System.out.println("[3] Delete a thing");
		System.out.println("[4] See all the stuff you can do");
		System.out.println("[0] Exit");
		System.out.print("What do you want to do? : ");
	}

	// Navigates the menu. You can include the print_menu code here, but I prefer not to. Looks cleaner for me.
	public void navigate_menu() {

		Scanner picker = new Scanner(System.in);
		String choice = null;
		
		try {
			do {
				choice = picker.nextLine();
				int option = Integer.valueOf(choice);
				switch (option) {
					case 1: random_pick();
							print_menu();
							break;
					case 2: add_stuff_todo();
							print_menu();
							break;
	
					case 3: delete_thing_to_do();
							print_menu();
							break;

					case 4: display_activities();
							print_menu();
							break;
	
					case 0: System.out.println("\n\nExiting...");
							break;
	
					default: System.out.println("Pick a number off the menu!");
						 	print_menu();
				}
			} while (!choice.equals("0"));
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			System.out.println("Goodbye.");
		}

	}

}