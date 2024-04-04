import com.esprit.gym_gui.models.Meal;
import com.esprit.gym_gui.repository.MealRepository;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNull;

public class MealRepositoryTest {
    @Test
    public void testDeleteMeal() {
        MealRepository mealRepository = new MealRepository();
        Meal meal = new Meal();
        meal.setId(1);
        meal.setName("test");
        meal.setCalories(100);
        meal.setProtein(10);
        meal.setCarbs(20);
        meal.setFat(5);
        meal.setSugar(5);
        meal.setUserId(1);
        meal.setDate(new Date(2021, 1, 1));
        mealRepository.save(meal);
        mealRepository.delete(1);
        assertNull(mealRepository.findById(1));
    }
}
