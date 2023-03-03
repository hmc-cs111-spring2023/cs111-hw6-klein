package machines

import regex._
import dfa._

// Char -> RegularLanguage
given Conversion[Char, RegularLanguage] = Character(_) 

// String -> RegularLanguage
given Conversion[String, RegularLanguage] = _.map(Character(_)).reduce(Concat(_, _))

extension (exp1: RegularLanguage)
    // Adding operators to RegularLanguage
    def || (exp2: RegularLanguage) = Union(exp1, exp2)

    def ~ (exp2: RegularLanguage) = Concat(exp1, exp2)

    def <*> = Star(exp1)

    def <+> = Concat(exp1, Star(exp1))

    def apply(n: Int) : RegularLanguage = {
        // Base case of n == 0 is epsilon, which represents an empty word.
        if (n == 0) then Epsilon
        else exp1 ~ (apply(n - 1))
    }

    // Contextual parameters: RegularLanguage -> DFA
    // The alphabet (a Set[Char]) must be given before this method is called.
    def toDFA(using givenAlph: Set[Char]) = regexToDFA(exp1, givenAlph)

// Implicit conversion: RegularLanguage -> DFA
given Conversion[RegularLanguage, DFA] = {exp =>
    regexToDFA(exp, chars(exp))
}

// Helper function that returns the set of characters that appear in the given regular expression.
def chars(exp: RegularLanguage) : Set[Char] = {exp match
    case Empty => Set()
    case Epsilon => Set()
    case Character(c) => Set(c)
    case Union(r1, r2) => chars(r1) ++ chars(r2)
    case Concat(r1, r2) => chars(r1) ++ chars(r2)
    case Star(r) => chars(r)
}
