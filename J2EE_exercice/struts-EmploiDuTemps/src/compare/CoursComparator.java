package compare;

import java.util.Comparator;

import entity.Attribution;

public class CoursComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Attribution etu1=(Attribution) o1;
		Attribution etu2=(Attribution) o2;
	
		return etu1.getNomCours().compareTo(etu2.getNomCours());
	}
}
