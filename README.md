# funky-j
Functional programming for java

-------------------------------
Getting started:

1st step: 
--------------
Queriable.create(Class<T> type)

2nd step: 
--------------
// your code here (see example)

3rd step:
--------------
execute(Collection<T> items)

-------------------------------
Example:
// only ages from a collection of 'Person' 
Queriable
  .create(Person.class)
-
  .orderBy(new Comparator<Person>() { public int compare(Person o1, Person o2) { return o1.age - o2.age; }})
-
  .delete(new Predicate<Person>() { public boolean predict(Person value) { return value.name.equals("roy"); }})
-
  .select(new Selector<Person, Integer>() { public Integer select(Person value) { return value.age; }});
-

// look at the file QueriableTest for more code examples
// or just use the classes... it's pretty straight forward

:)
