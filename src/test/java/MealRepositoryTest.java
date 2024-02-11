import com.esprit.gui.models.Meal;
import com.esprit.gui.repository.MealRepository;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class MealRepositoryTest {


    public Meal generateMeal() {
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
        return meal;
    }

    @Test
    public void ADD_MEAL_RETURNS_THE_GIVEN_MEAL() {
        MealRepository mealRepository = new MealRepository();
        Meal meal = generateMeal();
        Meal returnedMeal = mealRepository.save(meal);
        System.out.println(returnedMeal);
        assertNotNull(returnedMeal);
    }

    @Test
    public void DELETE_MEAL_RETURNS_NULL() {
        MealRepository mealRepository = new MealRepository();
        Meal meal = generateMeal();
        mealRepository.save(meal);
        meal = mealRepository.getLastAddedMeal();
        mealRepository.delete(meal.getId());
        Meal returnedMeal = mealRepository.findById(meal.getId());
        assertEquals(returnedMeal.getId(), 0);

    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt((int) Math.floor(Math.random() * characters.length())));
        }
        return result.toString();
    }

    @Test
    public void UPDATE_MEAL_RETURNS_THE_GIVEN_MEAL() {
        MealRepository mealRepository = new MealRepository();
        Meal meal = generateMeal();
        meal.setCalories(77);
        mealRepository.save(meal);
        meal = mealRepository.getLastAddedMeal();
        meal.setName(this.generateRandomString(10));
        mealRepository.update(meal);
        Meal returnedMeal = mealRepository.findById(meal.getId());
        System.out.println(returnedMeal);
        assertEquals(returnedMeal.getName(), meal.getName());
    }
}
