package artur.sanko.helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

public interface CollectionsHelper {

    static Optional<String> getRandomElement(Collection<String> collection) {

        if (!collection.isEmpty()) {

            int randomIndex = new Random().nextInt(collection.size());
            int index = 0;

            Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {

                String item = iterator.next();
                if (index == randomIndex) {

                    return Optional.of(item);
                }

                index++;
            }
        }

        return Optional.empty();
    }
}
