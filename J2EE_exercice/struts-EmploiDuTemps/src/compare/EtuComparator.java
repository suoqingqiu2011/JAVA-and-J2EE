package compare;

import java.util.Comparator;

import entity.Attribution;


@SuppressWarnings("rawtypes")
public class EtuComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Attribution etu1=(Attribution) o1;
		Attribution etu2=(Attribution) o2;
		
		return etu1.getEtu().compareTo(etu2.getEtu());
	}
}
