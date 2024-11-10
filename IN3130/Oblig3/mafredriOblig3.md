# Mandatory Assignment 3

## Student Name:
mafredri

**Comments concerning this mandatory assignment:**
- This assignment consists of two exercises, and each student must turn in their own, independent solution to both.
- Exercise 1 and 2 requires a textual answer, including some discussion.
- In the event of minor errors or ambiguities in the assignment text,
you are generally expected to state your reasonable assumptions in your answer.
- For 3-day extension, submit your request in the [nettskjema](https://nettskjema.no/a/356843/) prior to the deadline.
- Answers must be in this file.
- You can embed images in the markdown for textual answers using the format: `![Image Description](./image-path.jpg)`.

# Exercise 1 (Undecidability)
We say that a Turing machine M is a composition of Turing machines M1 and M2, and write:
M = M1 & M2
if, for every input x, M produces the same result or output as the one that results when M1 is first run on input x,
and then M2 is run with the output of M1 as input. Or put more simply, when M always produces the same output as M1 “followed by” M2.

## Question a 
Formulate the Turing machine composition problem (the problem of deciding whether, when given three machines as input, the first is a composition of the remaining two) as a formal language.

### Answer
Math format:
$L = \{ M, M_1, M_2 \mid M(x) = M_2(M_1(x)) \} $

Normal format:
L = { M, M1, M2 | M(x) = M2(M1(x)) }

## Question b
Prove that the Turing machine composition problem is undecidable. 
Your proof should be complete – i.e., include the proof that the reduction algorithm exists, by describing 
that algorithm in sufficient detail.

### Answer
We can prove it is undecidable by doing a reduction from the Halting Problem.
The Halting Problem (HP) is a problem that states that there doesnt exist a machine that cant decide of another
machine halts or not.

We have the following machines H(M,x) and D(M,x) where D runs forever if M gives a NO and stops if it gives a YES.
H says YES if M halts and NO if it doesnt halt.
If we give machine D the machine H with D as a input we get a contradiction since H says D halts on x but
the outer D that contains all the machines will do the opposite and run forever. Therefore you cant have a machine
H that can predict if a machine will halt or not.

This can be used in the composition problem where we have a reduction function that takes inn all the machines
and modifies them.

L = { M, M1, M2 | M(x) = M2(M1(x)) }

We can make M1 return 0 or 1 if it halts or not on x. M2 will give the opposite, if M1 gives 0 then M2 gives 1 and vice versa.
This will be the same as the HP where M2 is based on the computation of M1. If we send in M2 we will also get a contradiction
if we send in M2 as input.

# Exercise 2 (NP-completeness)
We know that 3-SATISFIABILITY is NP-complete. But there is of course nothing special about the number 3.

## Question a
Define 10-SATISFIABILITY and prove that it is NP-complete.

### Answer
3-SAT is a variant of the SAT problem. 

The SAT problem goes like this: 
C is a set of clauses that consists of literals that again consists of boolean variables from a set U.
The question is "are there a instance of U that makes all literals in C TRUE?". In other words, is there a truth
assignment which satesfies the set of clauses.

As previously mentioned, 3-SAT is a variant of the SAT problem. 3-SAT is very much alike but the literals in
the clauses in set C must consist of exactly 3 variables. This isnt a rule in the SAT problem.

As for 10-SAT each literal must consist of exactly 10 variables instead of 3.

10-SAT is NP-COMPLETE since its in NP and NP-HARD:
- NP, we can verify that the clause is satesfied in polynomial time, gives out combined boolean value TRUE.
- NP-HARD, we can reduce the problem to 3-SAT which is NP-COMPLETE which is a subset of NP-HARD.

As for the proof I will do a reduction from 3-SAT to 10-SAT. The reduction will consist of turning each literal
from a 3-SAT format to a 10-SAT format.
Lets say we have the following literal:

(x1 v x2 v x3)

This can be transformed into 
(x1 v x2 v x3 v y1 v y2 v y3 v y4 v y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v y3 v y4 v y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v -y2 v y3 v y4 v y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v -y3 v y4 v y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v y3 v -y4 v y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v y3 v y4 v -y5 v y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v y3 v y4 v y5 v -y6 v y7) and
(x1 v x2 v x3 v -y1 v y2 v y3 v y4 v y5 v y6 v -y7) and
(x1 v x2 v x3 v -y1 v -y2 v -y3 v y4 v y5 v y6 v y7) and

... and
(x1 v x2 v x3 v -y1 v -y2 v -y3 v -y4 v -y5 v -y6 v y7) and
(x1 v x2 v x3 v -y1 v -y2 v -y3 v -y4 v -y5 v -y6 v -y7) and

This works since the new variables y_n doesnt have a affect on the actual problem (x1, x2 and x3), they're
just there to fill inn the space to fullfill the requirement.

Creating the literal takes only 2^(n-m) new variables which is polynomial time making the reduction valid.



## Question b
What is the complexity of 10-SATISFIABILITY when the number of variables is limited to 100? Prove your answer.

### Answer
If we're limited to only 100 variables then we have a upper limit to how many steps we need to take to solve the problem.
The solution can be found in O(2^n) time by going over the variables in U and set them for either TRUE or FALSE and test
each possible combination. By setting a upper limit the time it takes can be set to O(2^100) which means we're
working with a constant which is rounded down to only 1. Therefor it can be solved in O(1) time.

## Delivery:
1. Filename: rename this file to: username + Oblig3 (e.g. usernameOblig3.md)
2. Either:
   - If pictures are included; Create a folder with your username + oblig3, zip that folder with the same name
   - If no pictures; just deliver this file 
3. Deliver it [devilry](https://devilry.ifi.uio.no/)
