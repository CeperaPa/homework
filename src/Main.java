import animals.AbsAnimal;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandsData;
import factory.AnimalFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {

        List<AbsAnimal> animals = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        CommandsData[] commandsData = CommandsData.values();
        String[] commandsString = new String[CommandsData.values().length];
        for (int i = 0; i < commandsString.length; i++) {
            commandsString[i] = commandsData[i].name().toLowerCase();
        }

        while (true) {

            System.out.printf("Введите команду: %s\n", String.join(" или ", commandsString));

            String commandStr = scanner.next().trim().toUpperCase();

            boolean isCommandIncorrect = false;
            for (CommandsData command : commandsData) {
                if (command.name().equals(commandStr)) {
                    isCommandIncorrect = true;
                    break;
                }
            }

            if (!isCommandIncorrect) {
                System.out.println("Ошибка! Вы ввели неверную команду");
                continue;
            }

            CommandsData userCommandData = CommandsData.valueOf(commandStr);
            switch (userCommandData) {
                case ADD:
                    AnimalFactory animalFactory = new AnimalFactory();

                    AnimalTypeData[] animalTypesData = AnimalTypeData.values();
                    String[] animalTypeStr = new String[animalTypesData.length];
                    for (int i = 0; i < animalTypesData.length; i++) {
                        animalTypeStr[i] = animalTypesData[i].name().toLowerCase();
                    }
                    AbsAnimal userAnimal;
                    while (true) {
                        System.out.printf("Введите животное: %s\n", String.join(" или ", animalTypeStr));
                        String userAnimalStr = scanner.next().trim().toUpperCase();

                        boolean isAnimalIncorrect = false;
                        for (AnimalTypeData command : animalTypesData) {
                            if (command.name().equals(userAnimalStr)) {
                                isAnimalIncorrect = true;
                                break;
                            }
                        }
                        if (!isAnimalIncorrect) {
                            System.out.println("Ошибка! Вы ввели неверное животное. Попробуйте ещё раз.");
                            continue;
                        }
                        AnimalTypeData animalTypeData = AnimalTypeData.valueOf(userAnimalStr);
                        userAnimal = animalFactory.create(animalTypeData);
                    }

                    System.out.println("Введите имя животного");
                    String name = scanner.next().trim();

                    userAnimal.setName(name);

                    System.out.println("Введите возраст животного");
                    // защита от дурака на int (как вариант через try catch)
                    int age = Integer.parseInt(scanner.next().trim());
                    userAnimal.setAge(age);

                    System.out.println("Введите вес животного");
                    // защита от дурака на int (как вариант через try catch)
                    float weight = Float.parseFloat(scanner.next().trim());
                    userAnimal.setWeight(weight);

                    System.out.println("Введите цвет животного");
                    // защита от дурака + вывести варианты, которые в ENUMе
                    ColorData colorData = ColorData.valueOf(scanner.next().trim().toUpperCase());
                    userAnimal.setColor(colorData);

                    animals.add(userAnimal);
                    break;
                case LIST:
                    for (AbsAnimal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                case EXIT:
                    System.out.println("Вы ввели команду exit. До новых встреч!");
                    System.exit(0);
            }
        }
    }
}