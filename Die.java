package eecs2030.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Random;

/**
 * A class that represents an n-sided die where the sides are decorated with a
 * string. Every Die has at least one face.
 * 
 * <p>
 * Implementation Details: Every n-sided Die object has-a sorted map that stores
 * the mapping between the face number 1, 2, ..., n to the corresponding face
 * string. For example, a 4-sided die whose face strings are "ONE", "TWO",
 * "THREE" and "FOUR" would have the following map:
 * 
 * <table summary="Map of face numbers to face strings">
 * <tr>
 * <th>Key&nbsp;&nbsp;&nbsp;</th>
 * <th>Value</th>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>"ONE"</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>"TWO"</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>"THREE"</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>"FOUR"</td>
 * </tr>
 * </table>
 * 
 * <p>
 * The die also stores it current value as an integer between 1 and n. To return
 * the current value of the die, the die uses its current value as a key and
 * returns the corresponding value in the map (the current face string).
 * 
 * <p>
 * To roll a die, the die sets its current value to a random value between 1 and
 * n, and returns the string of the current face.
 * 
 */
public class Die {

	private SortedMap<Integer, String> valueMap;

	private int currentvalue = 1;

	/**
	 * Initializes an n-sided die where the sides are decorated with the strings
	 * in the specified array. Each string in the array is assigned to exactly
	 * one face of the die. The die will have as many faces as there are strings
	 * in the array. For example:
	 * 
	 * <pre>
	 * String[] faces = { "A", "A", "E", "E", "G", "N" };
	 * Die d = new Die(faces);
	 * </pre>
	 * 
	 * <p>
	 * would construct a 6-sided die where the sides are lettered
	 * <code>"A"</code>, <code>"A"</code>, <code>"E"</code>, <code>"E"</code>,
	 * <code>"G"</code>, and <code>"N"</code>.
	 * 
	 * <p>
	 * The mapping of the letters to the die faces is unspecified; all that is
	 * guaranteed is that each letter in the given string is mapped to one and
	 * only one face of the die.
	 * 
	 * <p>
	 * The current value of the die is unspecified; calling
	 * <code>getValue()</code> immediately after constructing a die could return
	 * any face that belongs to the die.
	 * 
	 * @param faces
	 *            an array of strings, one string for each face of the die
	 * 
	 * @throws IllegalArgumentException
	 *             if faces.length == 0
	 * 
	 */
	public Die(String[] faces) {
		if (faces.length == 0) {
			throw new IllegalArgumentException("Must have at least 1 face");
		}
		this.valueMap = new TreeMap<Integer, String>();
		for (int i = 1; i <= faces.length; i++) {
			this.valueMap.put(i, faces[i - 1]);
		}

	}

	/**
	 * Construct an independent copy of an existing die. The new die will have
	 * the same strings on the same faces as the existing die.
	 * 
	 * <p>
	 * The current value of this die will be the same as the other die.
	 * 
	 * @param other
	 *            the die to copy
	 */
	public Die(Die other) {
		this.valueMap = new TreeMap<Integer, String>(other.valueMap);

	}

	/**
	 * Returns the number of faces that this die has.
	 * 
	 * @return the number of faces that this die has
	 */
	public int getNumberOfFaces() {

		return this.valueMap.lastKey();

	}

	/**
	 * Rolls the die to a new random face, and returns the string on the face.
	 * 
	 * @return the string on face after rolling the die
	 */
	public String roll() {
		int max = this.valueMap.lastKey();
		int rand = (int) (max * Math.random() + 1);

		this.currentvalue = rand;

		return this.valueMap.get(rand);

	}

	/**
	 * Returns the string corresponding to the current face value of the die.
	 * 
	 * @return the string corresponding to the current face value of the die
	 */
	public String getValue() {
		return this.valueMap.get(this.currentvalue);
	}

	/**
	 * Returns the mapping of face numbers to strings for this die. The faces
	 * are numbered using the <code>Integer</code> values <code>1</code> through
	 * <code>n</code> where <code>n</code> is the number of sides of the die,
	 * and the returned map is sorted on its keys (the face numbers). For
	 * example, the die with faces:
	 * 
	 * <p>
	 * <code>1, 2, 3, 4, 5, 6</code>
	 * 
	 * <p>
	 * having face strings:
	 * 
	 * <p>
	 * <code>"C", "M", "I", "O", "U", "T"</code>
	 * 
	 * <p>
	 * would return the map whose <code>toString</code> method would produce the
	 * following string:
	 * 
	 * <p>
	 * <code>{1=C, 2=M, 3=I, 4=O, 5=U, 6=T}</code>
	 * 
	 * <p>
	 * Clients are unable to modify the mapping of faces to letters using the
	 * returned map; i.e., modifying the returned map has no effect on the die.
	 * 
	 * @return a sorted map of the faces to letters
	 */
	public SortedMap<Integer, String> getValueMap() {
		SortedMap<Integer, String> map = new TreeMap<Integer, String>(this.valueMap);

		return map;

	}

	/**
	 * Returns a hash code for this die. The returned hash code is equal to the
	 * sum of the hash codes of the strings on the faces of the die.
	 * 
	 * @return an integer hash code
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 1; i <= this.valueMap.lastKey(); i++) {
			String str = this.valueMap.get(i);
			sum = sum + str.hashCode();
		}

		return sum;
	}

	/**
	 * Compares this die to the specified object. The result is
	 * <code>true</code> if and only if all of the following are
	 * <code>true</code>:
	 * 
	 * <ul>
	 * <li>the argument is not <code>null</code></li>
	 * <li>the argument is a <code>Die</code> reference</li>
	 * <li>the strings corresponding to the current face values of this die and
	 * the other die are <code>equals</code></li>
	 * <li>both dice have the same number of faces</li>
	 * <li>both dice have the same face strings</li>
	 * </ul>
	 * 
	 * <p>
	 * Note that two dice can be <code>equals</code> if their mappings of faces
	 * to strings are different; as long as both dice contain the exact same
	 * strings it is possible for the dice to be <code>equals</code>. For
	 * example, consider two dice with the following mappings:
	 * 
	 * <table summary="Map of face numbers to face strings">
	 * <tr>
	 * <th>Key&nbsp;&nbsp;&nbsp;</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>"the"</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>"for"</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>"of"</td>
	 * </tr>
	 * <tr>
	 * <td>4</td>
	 * <td>"to"</td>
	 * </tr>
	 * </table>
	 * 
	 * 
	 * <table summary="Map of face numbers to face strings">
	 * <tr>
	 * <th>Key&nbsp;&nbsp;&nbsp;</th>
	 * <th>Value</th>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>"to"</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>"for"</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>"of"</td>
	 * </tr>
	 * <tr>
	 * <td>4</td>
	 * <td>"the"</td>
	 * </tr>
	 * </table>
	 * 
	 * <p>
	 * If the first die has a current value of 1 and the second die has a
	 * current value of 4, then the two dice would be equal because both dice
	 * have the same face strings and the current value of both dice is the
	 * string "the".
	 * 
	 * @param obj
	 *            the object to compare
	 * @return <code>true</code> if the two dice are equal (see above), and
	 *         <code>false</code> otherwise
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		Die newdie = new Die((Die) (obj));
		if (newdie.getNumberOfFaces() != this.getNumberOfFaces()) {
			return false;
		}
		if (!this.getValue().equals(newdie.getValue())) {
			return false;
		}

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for (int i = 1; i <= this.getNumberOfFaces(); i++) {
			list1.add(this.valueMap.get(i));
			list2.add(newdie.valueMap.get(i));
		}

		boolean b = false;
		for (int i = 1; i <= this.getNumberOfFaces(); i++) {
			String current = this.valueMap.get(i);
			if (newdie.valueMap.containsValue(current) && (Collections.frequency(list1,
					this.valueMap.get(i)) == Collections.frequency(list2, newdie.valueMap.get(i)))) {
				b = true;
			} else {
				b = false;
				break;
			}
		}
		return b;

	}

	/**
	 * Returns a string representation of this die. The string are the strings
	 * of the faces separated by a comma and space. The strings appear in order
	 * of their numeric mapping. For example, the die with faces:
	 * 
	 * <p>
	 * <code>1, 2, 3, 4, 5, 6</code>
	 * 
	 * <p>
	 * having face strings:
	 * 
	 * <p>
	 * <code>"C", "M", "I", "QU", "U", "T"</code>
	 * 
	 * <p>
	 * has the string representation <code>"C, M, I, QU, U, T"</code>.
	 * 
	 * 
	 * @return a string representation of this die
	 * 
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 1; i <= this.getNumberOfFaces(); i++) {
			s += this.valueMap.get(i);
			if (i == this.getNumberOfFaces()) {
				break;
			}
			s += ", ";

		}

		return s;

	}

}
