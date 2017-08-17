package comparator;

import model.Advertisement;

import java.util.Comparator;

/**
 * The advertisements are sorted by the most recent update.
 */
public class SortByDate implements Comparator<Advertisement> {

    @Override
    public int compare(Advertisement o1, Advertisement o2) {
        return (o2.getCreatedDate().compareTo(o1.getCreatedDate()));
    }
}
