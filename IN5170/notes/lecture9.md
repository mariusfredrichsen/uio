# Types
- Detecting errors
    - static, before execution
    - dynamic, error message in runtime
- programming patterns
- abstraction, providing a interface and hides memory/details
- specifies intended behaviour

### Type for expressions
- expression 'e' has a 'type T' if 'e' will always evaluate to a value of 'type T'

### Type systems
- if we have an abstraction then we need to make sure that the program adheres to them
- a method to check if the program adheres

- static, checks in compile time
- dynamic, checks in run time

- decidable, more precise types and abstract less
- undecidable, type systems can become undecidable ????

- strong, aim to cover as many possible errors
- weak, gives more freedom

### Type soundness
- if the program adheres during compile time then it also adheres during runtime
- e_1 ^-v-> e_2

- reachability
    - results error state (runtime?)
    - run the program and do one step at a time and check for each step

- reduction
    - block the program if it isnt well typed
    - dont process anything unless it is well typed

### Completeness of Type Systems
- types and logic share some properties
- static types, typically incomplete
    - decidable
- dynamic types, complete but detect the error too late

### Simple Type System
- discipline
    - type syntax
    - subtyping relation
    - typing enviroment
    - type judgment
    - set of type rules (system iteself)
    - notion of type soundness

- sequential program
    - syntax:
        - e, integer and boolean literals:
            - e::= n | true | false | e + e | e ^ e | e <= e
            - T ::= Bool | Int
    - judgment:
        - |- e : T
        - e is well-typed with type T
    - rules:
        - one conclusion
        - list of premises
        - if all premises are true then the conclusion is also true
        - a rule without premises is an axiom, something is always true
        - axioms:
            - |- false : Bool
            - |- true : Bool
            - |- n : Int

    - typing tree
        - a tree where all nodes is a type rule application on a concrete expression
            - is closed if all leaves are stemming from axioms

    - termination
        - an evaluation of expressions e_1 successfully terminates if:
        - e_1 ^-v-> ... ^-v-> e_final
    
    - soundness
        - 3 requirements
            1. all expressions that are successfully terminated are well-typed
            2. if a well-typed expression can evaluate, then the result is well-typed
            3. if a well-typed expression is not successfully terminated, then it can evaluate
    
    - preservation
        - for all e, e´, T. ((e : T and e ^-v-> e´) --> e´ : T)
    - progress
        - for all e, T. ((e : T and not term(e)) --> there exists e´. e ^-v-> e´)
        - if it is not terminated then we can progress/evaluate

- concurrency
    - typing of variables
        - requires a track of which variables are declared
        - records the information in a type enviroment
    - subtyping
        - relation between types
        - ever type is a sub-type of itself
        - T <: T, T-refl
        - (( T_1 <: S ) x (S <: T_2) ) / T_1 <: T_2, T-trans
        - Int <: Number
        - (( S <: T ) x (|´ |- e : S) ) /  (|´ |- e : T)
    
    - enviroment
        - |´ = {v |-> Int, w |-> Int }
        - variable v and w has type Int
        - |´[x |-> T] = |´´
        - empty enviroment = Ø
        - no type on variable: |´(x) = \_|_

        - judgment
            - |´ |- e : T
            - e has type T if all variables are described by |´

### Evaluation
- preservation
    - ∀Γ, e, e′, T. (Γ ⊢ e : T ∧ e ⇝ e′) → Γ ⊢ e′ : T
- progress
    - ∀Γ, e, T. (Γ ⊢ e : T ∧ ¬term(e)) → ∃e′. e ⇝ e′

