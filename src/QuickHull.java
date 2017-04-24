/**
* Subject: 	Programación de Aplicaciones Interactivas
* Practice: P11 QuickHull
* E-mail:   alu0100881677@ull.edu.es 
* Date:     20/4/2017
* Program:  The class at hand is in charge of calculate the convex component.
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.awt.Point;
import java.util.ArrayList;

/**
 * Definition of the class QuickHull
 * @author Guille
 *
 */
public class QuickHull {
	//private attributes
	private boolean running;
	private ArrayList<ArrayList<Point>> evolution;
	
	/**
	 * Constructor of the class
	 */
	public QuickHull() {
		running = false;
		evolution = new ArrayList<>();
	}

	/**
	 * Starts the process for calculate the convex component
	 * @param points
	 * @return
	 */
	public ArrayList<Point> run(ArrayList<Point> points) {
		//we initialize the convex component at void
		ArrayList<Point> convexHull = new ArrayList<Point>();
		
		//if the number of points is less than 3 we do not have to calculate the convex component
		if (points.size() < 3)
			return (ArrayList) points.clone();
		
		//initialize the necessary variables for calculate the rightmost point and the leftmost point
		int minPoint = -1, maxPoint = -1;
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		//with this loop we calculate the extreme side points
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).x < minX) {
				minX = points.get(i).x;
				minPoint = i;
			}
			if (points.get(i).x > maxX) {
				maxX = points.get(i).x;
				maxPoint = i;
			}
		}
		//get out the points selected
		Point A = points.get(minPoint);
		Point B = points.get(maxPoint);
		
		//add them to the convex component
		convexHull.add(A);
		convexHull.add(B);
		
		//we create two points set for calculate the left located points and the right located points of the actual line
		ArrayList<Point> leftSet = new ArrayList<Point>();
		ArrayList<Point> rightSet = new ArrayList<Point>();
		
		//we add each point to the convinient set
		for (int i = 0; i < points.size(); i++) {
			Point p = points.get(i);
			if (pointLocation(A, B, p) == -1)
				leftSet.add(p);
			else
				rightSet.add(p);
		}
		
		//call hullset
		hullSet(A, B, rightSet, convexHull);
		hullSet(B, A, leftSet, convexHull);
		return convexHull;
	}

	/**
	 * Calculate the distance of point C to the line defined by A--B.
	 */
	public int distance(Point A, Point B, Point C) {
		int ABx = B.x - A.x;
		int ABy = B.y - A.y;
		int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
		if (num < 0)
			num = -num;
		return num;
	}
	/**
	 * 
	 * @param A
	 * @param B
	 * @param set
	 * @param convexHull
	 */
	public void hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> convexHull) {
		//la posición de inserción es el índice del punto B.
		//the insertion point is the index of the point B in the arrayList convexHull
		int insertPosition = convexHull.indexOf(B);
		//no point in the set carry nothing to calculate, base case.
		if (set.size() == 0)
			return;
		//if the set has only one point, that point is part of the convex Component, base case.
		if (set.size() == 1) {
			Point p = set.get(0);
			set.remove(p);
			convexHull.add(insertPosition, p);
			evolution.add(new ArrayList<>(convexHull));
			return;
		}
		
		//this loop calculates the index of the furthest point in the vector.
		int dist = Integer.MIN_VALUE;
		int furthestPoint = -1;
		for (int i = 0; i < set.size(); i++) {
			Point p = set.get(i);
			int distance = distance(A, B, p);
			if (distance > dist) {
				dist = distance;
				furthestPoint = i;
			}
		}
		
		//add the point to the convex component
		Point P = set.get(furthestPoint);
		set.remove(furthestPoint);
		convexHull.add(insertPosition, P);
		evolution.add(new ArrayList<>(convexHull));
		
		// Calculate the points that are placed at the left of the line A---->P.
		ArrayList<Point> leftSetAP = new ArrayList<Point>();
		for (int i = 0; i < set.size(); i++) {
			Point M = set.get(i);
			if (pointLocation(A, P, M) == 1) { //si la función retorna uno el punto esta a la izquierda
				leftSetAP.add(M);
			}
		}
		
		// Calculate the points that are placed at the left of the line P--->B
		ArrayList<Point> leftSetPB = new ArrayList<Point>();
		for (int i = 0; i < set.size(); i++) {
			Point M = set.get(i);
			if (pointLocation(P, B, M) == 1) {
				leftSetPB.add(M);
			}
		}
		//Recursive call
		hullSet(A, P, leftSetAP, convexHull);
		hullSet(P, B, leftSetPB, convexHull);
	}
	
	/**
	 * This function calculates if the point P is placed at the left(1) or 
	 * at the right(-1) of the line A--B
	 * @param A
	 * @param B
	 * @param P
	 * @return
	 */
	public int pointLocation(Point A, Point B, Point P) {
		int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
		return (cp1 > 0) ? 1 : -1;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}
	
	public ArrayList<ArrayList<Point>> getEvolution(){
		return evolution;
	}

	/**
	 * @param running
	 *          the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
}
