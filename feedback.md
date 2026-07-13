**NG 7/13/2026**
* To provide you with thorough feedback, your instructor reads your code line by line and runs it, and quite often does it multiple times. Jumping from file to file is too time-consuming. **Unless noted otherwise, place your class definitions above the main(), all in one file.** While the industry standard involves multi-source file programs, we will use a single file format for these labs unless instructed otherwise.
* never hesitate to ask if you find something confusing or have questions
*  **feedback.md is for instructor use only.  Please DO NOT change the feedback.md**; make a copy if needed; do no add anything
* if any, items with (-X) - no deductions this time, serve as a warning; please ensure these errors are corrected, as repeating them in future assignments will result in X points being deducted
* in feedback, #N means line number, e.g., 
* 20 and like readability: do not put opening and closing {} on the same line; each executable statement should be on its own line; define each variable on a separate line; do not put closing } and the next statement on the same line;  closing } should be on its own line-1
```text
int health=DEFAULT_HEALTH;
int strength = DEFAULT_STRENGTH;
public Creature() {
  setCreature("unknown", "unknown", 1, 1);
}

```
* 47and like: why not simply return health;? 
* #129-132 and like: excessive use of System.out.println(); method calls are computational expense (takes time and space); unnecessary function calls increase execution time and require additional resources -2
```text
System.out.println("\n\nCode is like humor.\n\t"+ 
                    "When you have to explain "+ 
                    "it, it’s bad.\n\t\t\t"+ 
                    "–Cory House");
```
* 138- redundant; should have created a method to generate health and strength, collect name and type of a creature,  and call it twice, one for each creature; you can pass  1 or to as a part of the parameter list for Creature 1, Creature 2 -2

* #148 and like: what if numbers/limits/lengths change? Will you remember to update them everywhere where they are being used? What are the chances that you or whoever maintains the code forgets to change it in one or more places?  Do not hard-code numeric values other than 1 and zero; If it is not 1 or 0, make it “const” by using the final keyword –5 

* poor Id(s)  and/or inconsistent naming convention; ids should be self-documenting and as short as possible; use verbs for functions and nouns for variables; use camel-casing for variables (errorMessage) enum & const should be in upper case with words separated by “_”;  e.g., MAX_TERMS, PRINT_RECORDS; flags isValid (clear what true would mean); if copying – e.g rhsQueue or scrQueue  ( rhs- right-hand side, src – source); do not use IDs that are same as or very similar to Java keywords and functions; no need to call an array “array”- it is obvious; vars should not be called value; one char identifiers (e.g. j,k, i) are only suitable for loop counters; never use idetifiers such as object or var  -2
``` text
Creature c1, Creature c2

```
* to repeat the same character multiple times (-1)
```text
char c = '-'; int n = 5;
String repeated = String.valueOf(c).repeat(n); System.out.println(repeated); // Output: -----

```
* do not resubmit
***
