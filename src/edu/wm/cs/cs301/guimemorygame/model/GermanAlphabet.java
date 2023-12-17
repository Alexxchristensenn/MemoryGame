package edu.wm.cs.cs301.guimemorygame.model;
import java.util.*;

public class GermanAlphabet implements Alphabet {
	
	private static List<Integer> germanCodePoints = Arrays.asList
		(0x0041, 0x0042, 0x0043, 0x0044, 0x0045, 0x0046,
		 0x0047, 0x0048, 0x0049, 0x004A, 0x004B, 0x004C,
		 0x004D, 0x004E, 0x004F, 0x0050, 0x0051, 0x0052,
		 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058,
		 0x0059, 0x005A, 0x00C4, 0x00D6, 0x00DC, 0x00DF);

	@Override
	public char[] toCharArray() {
		Collections.shuffle(germanCodePoints);
		char[] charset = new char[germanCodePoints.size()];
		for (int i=0; i < germanCodePoints.size(); i++) {
			int temp = (int) germanCodePoints.get(i);
			charset[i] = (char) temp;
		}
		return charset;
	}
	
}
