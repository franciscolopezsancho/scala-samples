find out what's with MODULE, what it means

Maybe for the first commit. 

A value class is a class that will not allocate itself as an object at runtime. 
must extend AnyVal or an universal trait, and have just one value field.
Can only extend from universal traits ( UT extends Any, only defs, no inicialization )

Correctness:

while providing type checking you don't pay the overhead of object allocation


UNAVOIDABLE ALLOCATION

1. When extends an universal trait and is used as such. i.e. a method that accepts as input that universal trait
2. When is used in an Array
3. When is used it's type is tested at runtime. i.e when pattern matching


















Add the jar from the beginning
