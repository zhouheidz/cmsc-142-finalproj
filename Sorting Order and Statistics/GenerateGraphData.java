import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateGraphData {

	public static ArrayList<Integer> sortAsc(ArrayList<Integer> numbers) {
		Collections.sort(numbers);
		return numbers;
	}

	public static ArrayList<Integer> sortDesc(ArrayList<Integer> numbers) {
		Collections.sort(numbers, Collections.reverseOrder());
		return numbers;
	}

	public static ArrayList<Integer> genRandIntArray(int size) {
		ArrayList numbers = new ArrayList<Integer>();
		
		for(int i = 0; i < size; i++) {
			numbers.add(ThreadLocalRandom.current().nextInt(0, 1000));
		}

		return numbers;

	}

	public static void main(String[] args) {
		try {
			File fout = new File("random.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			ArrayList numbers = new ArrayList<Integer>();

			for(int i = 1; i <= 100; i++) {
				numbers = new ArrayList<Integer>();
				numbers = genRandIntArray(i);
				for(int j = 0; j < i; j++) {
					bw.append(numbers.get(j) + " ");
				}
				bw.newLine();
			}
			bw.close();

			fout = new File("ascending.txt");
			fos = new FileOutputStream (fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));

			for(int i = 1; i <= 100; i++) {
				numbers = new ArrayList<Integer>();
				numbers = sortAsc(genRandIntArray(i));
				for(int j = 0; j < i; j++) {
					bw.append(numbers.get(j) + " ");
				}
				bw.newLine();
			}
			bw.close();

			fout = new File("descending.txt");
			fos = new FileOutputStream (fout);
			bw = new BufferedWriter(new OutputStreamWriter(fos));

			for(int i = 1; i <= 100; i++) {
				numbers = new ArrayList<Integer>();
				numbers = sortDesc(genRandIntArray(i));
				for(int j = 0; j < i; j++) {
					bw.append(numbers.get(j) + " ");
				}
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");   
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
	}
}