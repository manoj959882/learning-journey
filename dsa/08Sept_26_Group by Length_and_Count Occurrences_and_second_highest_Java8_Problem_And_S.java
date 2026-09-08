***********************Date 08 Sept se yaha se padhana hai:   ***********************************

        Every stream operation fits into 3 buckets:

        **Source** — where does the data come from? (list.stream(), Arrays.stream(), Stream.of())

        **Intermediate operations** — transformations that return another stream (filter, map, sorted, distinct, limit) — these are lazy, they don't run until a terminal operation is called

        **Terminal operation** — the operation that actually triggers execution and produces a result (collect, forEach, count, reduce, findFirst)

        One thing always remember — until you call a terminal operation, the stream doesn't run at all. It is lazy, meaning work only happens when you ask for the final result.

        ---

        ## TOPIC 1 — Group By Length

        Example: ["apple", "bat", "cat", "banana", "dog", "kiwi"]

        We want each word to be grouped by its length. All 3-letter words in one group, all 5-letter words in another group.

        **Output:** {3=[bat, cat, dog], 5=[apple, kiwi], 6=[banana]}

        ### Old Java Way

        ```java
import java.util.*;

public class GroupByLengthOldWay {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "bat", "cat", "banana", "dog", "kiwi");

        Map<Integer, List<String>> result = new HashMap<>();

        for (String word : words) {
            int length = word.length();
            // If this length is not already a key, create a new empty list
            if (!result.containsKey(length)) {
                result.put(length, new ArrayList<>());
            }
            result.get(length).add(word);
        }

        System.out.println(result);
    }
}
```

In this old approach, we had to manually check — 'Does this key already exist?' If not, create a new list. This is 4-5 lines of boilerplate code just for grouping.

### The Java 8 Stream Way

```java
import java.util.*;
        import java.util.stream.*;

public class GroupByLengthStream {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "bat", "cat", "banana", "dog", "kiwi");

        Map<Integer, List<String>> result = words.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(result);
    }
}
```

        ### Line-by-line breakdown:

        **words.stream()** → converts the List into a Stream (source)

**.collect(...)** → terminal operation, packages the final result

**Collectors.groupingBy(String::length)** → THIS is the real magic. It tells Java: "for every element, compute String::length, and use that as
the map key. Automatically create a list for each unique key and keep adding matching elements"

        ### What String::length actually means:

        ```java
// This is equivalent to writing:
words.stream().collect(Collectors.groupingBy(word -> word.length()));
        ```

String::length is just a shortcut for writing word -> word.length(). Whenever a lambda is just calling a method without any extra logic, we can use a method reference.

### Example with Count Instead of List:

        ```java
Map<Integer, Long> countByLength = words.stream()
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));
// Output: {3=3, 5=2, 6=1}
```

This is a downstream collector — inside groupingBy we can specify what to do with each group. Here we said 'just count in each group, don't create a list'.



        ### Practice Problems for Group By Length:

        **Basic:** Group a list of names by their first letter instead of length.//.collect(Collectors.groupingBy(name -> name.charAt(0)));
        "Amit"  → 'A'
        "Ankit" → 'A'
        "Rahul" → 'R'
        "Rohit" → 'R'
        "Priya" → 'P'

        **Basic:** Group a list of integers by whether they are even or odd (Collectors.groupingBy(n -> n % 2 == 0)).

        **Intermediate:** Group employee objects by their department name (Map<String, List<Employee>>). // .collect(Collectors.groupingBy(Employee::getDepartment));

        **Intermediate:** Group words by length, but instead of a List<String>, produce a Set<String> for each group (hint: Collectors.groupingBy(String::length, Collectors.toSet())).
Map<Integer, Set<String>> result = words.stream()
        .collect(Collectors.groupingBy(
                String::length,
                Collectors.toSet()
        ));
		
**Advanced:** Group employees by department, and for each department directly get the average salary instead of the employee list
        (Collectors.groupingBy(Employee::getDept, Collectors.averagingDouble(Employee::getSalary))).

Map<String, Double> averageSalaryByDepartment =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
				
**Advanced:** Group students by grade (A/B/C), and within each grade, further group by gender (nested/multi-level groupingBy).
input is : List<Student> students = Arrays.asList(
        new Student("Amit", "A", "Male"),
        new Student("Priya", "A", "Female"),
        new Student("Rahul", "B", "Male"),
        new Student("Neha", "B", "Female"),
        new Student("Rohit", "A", "Male"),
        new Student("Pooja", "C", "Female"),
        new Student("Arjun", "C", "Male")
);

A
 ├── Male   → [Amit, Rohit]
        └── Female → [Priya]

B
 ├── Male   → [Rahul]
        └── Female → [Neha]

C
 ├── Male   → [Arjun]
        └── Female → [Pooja]
Map<String, Map<String, List<Student>>> result =
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.groupingBy(Student::getGender)
                ));

Yahan outer groupingBy() grade ke liye hai, aur inner groupingBy() gender ke liye.

Aur agar question ho “har grade + gender combination ka count”, then:
groupingBy(
        Student::getGrade,
        groupingBy(
                Student::getGender,
        counting()
    )
            )



            ---

            ## TOPIC 2 — Find Second Highest Number

This is a very common interview question — given a list of numbers, find the second highest (second largest) unique number.

        [10, 20, 4, 45, 99, 99, 3] → answer should be 45 (because 99 is repeated, the distinct second highest is 45)

        ### Old Java Way

```java
import java.util.*;

public class SecondHighestOldWay {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 4, 45, 99, 99, 3};

        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num != highest) {
                secondHighest = num;
            }
        }

        System.out.println("Second highest: " + secondHighest);
    }
}
```

This is classic two-pointer logic — tracking both highest and secondHighest. Beginners often get confused when duplicates appear, which is why the num != highest check is important.

        ### Java 8 Stream Way

```java
import java.util.*;
        import java.util.stream.*;

public class SecondHighestStream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 4, 45, 99, 99, 3);

        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.println("Second highest: " + secondHighest.orElse(-1));
    }
}
```

        **.distinct()** → removes duplicate values (so 99, 99 becomes just one 99)

**.sorted(Comparator.reverseOrder())** → sorts descending: [99, 45, 20, 10, 4, 3]

        **.skip(1)** → skips the first (largest) element — this is the key trick

**.findFirst()** → now the "first" remaining element IS the second highest

This approach is very intuitive if you think — 'Remove the largest, then the first remaining number is the second highest'. skip(1) means 'skip the first element'.

        ### Why Optional<Integer> instead of plain int?

The Stream API returns Optional because it's possible the list is too small for a second highest to exist — like if the list only has one number. 
Optional forces us to handle that 'empty' case, otherwise we could get a NullPointerException.

        **.orElse(-1)** → if no value found, return -1 as default.

        ### Common Mistake:

Many students forget to use distinct(). If the list has [99, 99, 45] and you don't use distinct(), after skip(1) you'd still have 99 (because 99 appeared twice),
and the wrong answer would be 99 when the actual second highest is 45. Always think first — 'Can duplicates make the second highest incorrect?'

        ### Practice Problems for Second Highest:

        **Basic:** Find the third highest number (change skip(1) to skip(2)).

        **Basic:** Find the second lowest number instead (use Comparator.naturalOrder() instead of reverseOrder).

        **Intermediate:** Find the second highest salary from a List<Employee> (use .map(Employee::getSalary) before the rest of the pipeline).

        **Intermediate:** Find the second highest number WITHOUT using distinct() — but the list may have duplicates, and duplicates should still count as separate positions
        (rank-based, not value-based). Discuss how the logic changes.

**Advanced:** Find the second highest number in each sub-list of a List<List<Integer>> (combine with .map() over the outer list).

        **Advanced:** Given a List<Employee>, find the employee (not just the number) who has the second highest salary.

        ---

        ## TOPIC 3 — Count Occurrences

Given a list or a string, count how many times each element/character appears.

Example: ["apple", "banana", "apple", "orange", "banana", "apple"]
Output: {apple=3, banana=2, orange=1}

        ### Old Java Way

```java
import java.util.*;

public class CountOccurrencesOldWay {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        Map<String, Integer> countMap = new HashMap<>();

        for (String fruit : fruits) {
            if (countMap.containsKey(fruit)) {
                countMap.put(fruit, countMap.get(fruit) + 1);
            } else {
                countMap.put(fruit, 1);
            }
        }

        System.out.println(countMap);
    }
}
```

This is exactly the same pattern we saw in Topic 1 — check if the key exists, then increment the count or create a new entry.

### Java 8 Stream Way

```java
import java.util.*;
        import java.util.stream.*;

public class CountOccurrencesStream {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        Map<String, Long> countMap = fruits.stream()
                .collect(Collectors.groupingBy(fruit -> fruit, Collectors.counting()));

        System.out.println(countMap);
    }
}
```

        **Collectors.groupingBy(fruit -> fruit, ...)** → group by the element itself (the key is the fruit name)

**Collectors.counting()** → instead of collecting a list per group, just count how many elements landed in that group

This is an extension of Topic 1! The difference is only that earlier we grouped by String::length, now we're using the element itself 
as the group-key — fruit -> fruit. And we're using Collectors.counting() instead of the default list collector. This shows how powerful/reusable the groupingBy pattern is.

        ### Alternate One-Liner Using Collectors.toMap:

        ```java
Map<String, Long> countMap2 = fruits.stream()
        .collect(Collectors.toMap(f -> f, f -> 1L, Long::sum));
```

This is a slightly advanced approach. In toMap we give three things — what the key will be, what the value will be (initially 1), and if the key already exists,
how to merge old and new values (Long::sum — meaning add them together). This approach impresses in interviews because it shows you understand the merge function concept.

        ### Counting Characters in a String:

        ```java
String word = "programming";

Map<Character, Long> charCount = word.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

System.out.println(charCount);
```

        word.chars() gives us a stream of int values (ASCII codes), so .mapToObj(c -> (char) c) converts them back to Character objects.
This is a small gotcha that beginners need to know — String doesn't directly support .stream() like List does.




        ### Practice Problems for Count Occurrences:

        **Basic:** Count occurrences of each word in a sentence (split the sentence by spaces first).
String sentence = "java is easy and java is powerful";
java      → 2
is        → 2
easy      → 1
and       → 1
powerful  → 1

Map<String, Long> wordCount = Arrays.stream(sentence.split(" "))
        .collect(Collectors.groupingBy(
                word -> word,
                Collectors.counting()
        ));

System.out.println(wordCount);

or

Map<String, Long> wordCount = Arrays.stream(sentence.split(" "))
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
//word -> word aur Function.identity() yahan same kaam karte hain.


Students ke liye problem:

        1. Count each word
2. Count words case-insensitively
3. Find the most frequent word
4. Find the second most frequent word
5. Find words appearing more than once
6. Find words appearing exactly once
7. Sort words by frequency
8. Count only words whose length > 4
        9. Find the most frequent word starting with 'a'
        10. Remove duplicate words while preserving order

		
		
**Basic:** Count how many vowels appear in a given string.
        String str = "Hello World"; output is: Vowel count: 3

String str = "Hello World";

long count = str.chars() //This converts the string into an IntStream.
        .mapToObj(c -> (char) c) //Now we get actual characters:
        .filter(c -> "aeiouAEIOU".indexOf(c) != -1) //This checks whether the current character exists inside:
        .count(); //Finally, the stream counts the remaining characters.

System.out.println("Vowel count: " + count);



**Intermediate:** Find the most frequently occurring element in a list (hint: after groupingBy + counting, use .entrySet().stream().max(Map.Entry.comparingByValue())).

        **Intermediate:** Count occurrences of numbers in a list but only for numbers greater than a given threshold (combine filter before groupingBy).

        **Advanced:** Given a list of sentences, count the total occurrences of each word across ALL sentences combined (hint: flatMap to flatten all words first).

        **Advanced:** Find all elements that occur exactly once (hint: filter the resulting count-map where value == 1).

        ---

        ## Additional Practice Problems:


        2. Group numbers by even/odd and count them
List<Integer> numbers = Arrays.asList(
        1, 2, 3, 4, 5, 6, 7, 8, 10
);
Even = 5
Odd  = 4

Map<String, Long> result = numbers.stream()
        .collect(Collectors.groupingBy(
                number -> number % 2 == 0 ? "Even" : "Odd",
                Collectors.counting()
        ));

System.out.println(result);
Even → [2, 4, 6, 8, 10] → 5
Odd  → [1, 3, 5, 7]      → 4



        3. Find top 3 highest numbers from a list
List<Integer> top3 = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .collect(Collectors.toList());

System.out.println(top3); //[30, 25, 20]


4. Count vowels in each string of a list
List<String> words = Arrays.asList(
        "apple",
        "banana",
        "hello",
        "java"
);
apple  -> 2
banana -> 3
hello  -> 2
java   -> 2
Map<String, Long> result = words.stream()
        .collect(Collectors.toMap(
                word -> word,
                word -> word.chars()
                        .mapToObj(c -> (char) c)
                        .filter(c -> "aeiouAEIOU".indexOf(c) != -1)
                        .count()
        ));

System.out.println(result);//{apple=2, banana=3, hello=2, java=2}



5. Group employees by salary range and count
List<Employee> employees = Arrays.asList(
        new Employee("Amit", "IT", 45000),
        new Employee("Rahul", "HR", 55000),
        new Employee("Priya", "IT", 75000),
        new Employee("Neha", "Finance", 85000),
        new Employee("Rohit", "HR", 35000),
        new Employee("Pooja", "IT", 65000)
);
output:
Below 50K  -> 2
        50K - 70K  -> 2
Above 70K  -> 2
Map<String, Long> result = employees.stream()
        .collect(Collectors.groupingBy(
                employee -> {
                    double salary = employee.getSalary();

                    if (salary < 50000) {
                        return "Below 50K";
                    } else if (salary <= 70000) {
                        return "50K - 70K";
                    } else {
                        return "Above 70K";
                    }
                },
                Collectors.counting()
        ));

System.out.println(result);


Below 50K
    → Amit
    → Rohit
    → count = 2

        50K - 70K
    → Rahul
    → Pooja
    → count = 2

Above 70K
    → Priya
    → Neha
    → count = 2


        6. Find second most frequent element in a list
List<Integer> numbers = Arrays.asList(
        1, 2, 3, 2, 4, 1, 2, 3, 3, 3
);

3 → 4 times
2 → 3 times
1 → 2 times
4 → 1 time
So second most frequent element = 2.


Integer secondMostFrequent = numbers.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        )) //yah bana dega ki: 
1 → 2
        2 → 3
        3 → 4
        4 → 1
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .skip(1)
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(null);

System.out.println("Second most frequent: " + secondMostFrequent);//Second most frequent: 2




7. Group words by their starting letter and count
List<String> words = Arrays.asList(
        "apple", "ant", "banana", "ball",
        "cat", "car", "apricot", "dog"
);
a → 3
b → 2
c → 2
d → 1
Map<Character, Long> result = words.stream()
        .collect(Collectors.groupingBy(
                word -> word.charAt(0),
                Collectors.counting()
        ));

System.out.println(result);//{a=3, b=2, c=2, d=1}



8. Find numbers that appear more than once
List<Integer> numbers = Arrays.asList(
        1, 2, 3, 2, 4, 1, 5, 3, 6, 2
);
1 → 2 times
2 → 3 times
3 → 2 times
4 → 1 time
5 → 1 time
6 → 1 time

output is: [1, 2, 3]
List<Integer> duplicates = numbers.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

System.out.println(duplicates);



9. Group students by age and find average marks
List<Student> students = Arrays.asList(
        new Student("Amit", 20, 80),
        new Student("Rahul", 21, 70),
        new Student("Priya", 20, 90),
        new Student("Neha", 21, 80),
        new Student("Rohit", 20, 70)
);
output is:
Age 20 → 80.0
Age 21 → 75.0
Map<Integer, Double> result = students.stream()
        .collect(Collectors.groupingBy(
                Student::getAge,
                Collectors.averagingDouble(Student::getMarks)
        ));

System.out.println(result);//{20=80.0, 21=75.0}



10. Find the longest string and its frequency
List<String> words = Arrays.asList(
        "java",
        "stream",
        "programming",
        "java",
        "programming",
        "programming",
        "api"
);
output is: Longest string: programming
Frequency: 3
List<String> words = Arrays.asList(
        "java",
        "stream",
        "programming",
        "java",
        "programming",
        "programming",
        "api"
);

Map<String, Long> frequencyMap = words.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));//Sabse pehle frequency count karenge:
java         → 2
stream       → 1
programming  → 3
api          → 1


Map.Entry<String, Long> result = frequencyMap.entrySet()
        .stream()
        .max(Comparator.comparingInt(
                entry -> entry.getKey().length()
        ))
        .orElse(null);

if (result != null) {
        System.out.println("Longest string: " + result.getKey());
        System.out.println("Frequency: " + result.getValue());
        }




