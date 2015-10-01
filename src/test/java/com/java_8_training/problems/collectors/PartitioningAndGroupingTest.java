package com.java_8_training.problems.collectors;

import com.java_8_training.answers.collectors.Dish;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.java_8_training.answers.collectors.Dish.CaloricLevel;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static junit.framework.Assert.assertEquals;

//@Ignore
public class PartitioningAndGroupingTest {

    // See: Dish.menu.stream()
    // Partition the menu with dishes > 380
    @Test
    public void partitionDishes() {
        Map<Boolean, List<Dish>> partitionedDishes = Dish.menu.stream().collect(partitioningBy( d -> d.getCalories() > 380));

        assertEquals(2, partitionedDishes.get(false).size());
        assertEquals(7, partitionedDishes.get(true).size());
    }

    // Group the list of dishes by caloric level
    @Test
    public void groupDishes() {
        Map<CaloricLevel, List<Dish>> groupedDishes = Dish.menu.stream().collect(groupingBy(this::getCaloricLevel));

        assertEquals(4, groupedDishes.get(CaloricLevel.DIET).size());
        assertEquals(4, groupedDishes.get(CaloricLevel.NORMAL).size());
        assertEquals(1, groupedDishes.get(CaloricLevel.FAT).size());
    }

    private CaloricLevel getCaloricLevel(Dish dish) {
            if (dish.getCalories() <= 400)
                return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700)
                return CaloricLevel.NORMAL;
            else
                return CaloricLevel.FAT;
    }
}
